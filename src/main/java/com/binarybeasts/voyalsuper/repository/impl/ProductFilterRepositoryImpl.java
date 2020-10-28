package com.binarybeasts.voyalsuper.repository.impl;

import com.binarybeasts.voyalsuper.model.Product;
import com.binarybeasts.voyalsuper.model.Supermarket;
import com.binarybeasts.voyalsuper.model.dto.ProductFilterDto;
import com.binarybeasts.voyalsuper.model.dto.ProductPageDto;
import com.binarybeasts.voyalsuper.model.enums.ProductCategory;
import com.binarybeasts.voyalsuper.repository.ProductFilterRepository;
import com.binarybeasts.voyalsuper.repository.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ProductFilterRepositoryImpl implements ProductFilterRepository {

    private final EntityManager em;

    private final SupermarketRepository supermarketRepository;

    @Autowired
    public ProductFilterRepositoryImpl(EntityManager em, SupermarketRepository supermarketRepository) {
        this.em = em;
        this.supermarketRepository = supermarketRepository;
    }


    @Override
    public ProductPageDto getFilteredProductsByPage(ProductFilterDto productFilter, Integer page, Integer size, String orderColumn, boolean asc) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicates = getProductPredicates(cb, productFilter, product);

        Long count = getTotalRecords(cb, predicates);
        cq.where(predicates.toArray(new Predicate[0]));

        if(!"none".equals((orderColumn))){
            if(asc) cq.orderBy(cb.asc(product.get(orderColumn)));
            else cq.orderBy(cb.desc(product.get(orderColumn)));
        }

        TypedQuery<Product> typedQuery = em.createQuery(cq);
        typedQuery.setFirstResult(page * size);
        typedQuery.setMaxResults(size);
        List<Product> products = typedQuery.getResultList();
        return new ProductPageDto(products, count);

    }

    @Override
    public List<Product> getFilteredProducts(ProductFilterDto productFilterDto, String orderColumn, boolean asc) {
        return null;
    }

    private List<Predicate> getProductPredicates(CriteriaBuilder cb, ProductFilterDto productFilter, Root<Product> product) {
        List<Predicate> predicates= new ArrayList<>();

        if(productFilter.getName() != null && !"".equals(productFilter.getName())){
            predicates.add(cb.like(cb.lower(product.get("description")), "%" + productFilter.getName().toLowerCase() + "%"));
        }

        if(productFilter.getCategories() != null && !productFilter.getCategories().isEmpty()){
            Collection<ProductCategory> categories = productFilter.getCategories();
            Expression<ProductCategory> categoryExpression = product.get("category");
            predicates.add(categoryExpression.in(categories));
        }

        if(productFilter.getSupermarkets() != null && !productFilter.getSupermarkets().isEmpty()){
            Collection<Supermarket> supermarkets = supermarketRepository.findAllByNameIn(productFilter.getSupermarkets());
            List<Predicate> containsPredicate = new ArrayList<>();
            for(Supermarket supermarket : supermarkets){
                containsPredicate.add(cb.isMember(supermarket, product.get("marketsWithStock")));
            }
            predicates.add(cb.or(containsPredicate.toArray(new Predicate[0])));
        }

        return predicates;
    }

    private Long getTotalRecords(CriteriaBuilder cb, List<Predicate> predicates){
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Product.class)));
        em.createQuery(countQuery);
        countQuery.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(countQuery).getSingleResult();
    }

    }
