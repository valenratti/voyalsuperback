package com.binarybeasts.voyalsuper.preciosclarosapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PreciosClarosResponse {
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("maxLimitPermitido")
    private Integer maxLimitPermitido;

    @JsonProperty("productos")
    private List<Producto> productos = null;

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public PreciosClarosResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    public PreciosClarosResponse withTotal(Integer total) {
        this.total = total;
        return this;
    }

    @JsonProperty("maxLimitPermitido")
    public Integer getMaxLimitPermitido() {
        return maxLimitPermitido;
    }

    @JsonProperty("maxLimitPermitido")
    public void setMaxLimitPermitido(Integer maxLimitPermitido) {
        this.maxLimitPermitido = maxLimitPermitido;
    }

    public PreciosClarosResponse withMaxLimitPermitido(Integer maxLimitPermitido) {
        this.maxLimitPermitido = maxLimitPermitido;
        return this;
    }

    @JsonProperty("productos")
    public List<Producto> getProductos() {
        return productos;
    }

    @JsonProperty("productos")
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public PreciosClarosResponse withProductos(List<Producto> productos) {
        this.productos = productos;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Status:%d\nTotal:%d\n",status,total)+productos.toString();
    }

    public static class Producto {

        @JsonProperty("marca")
        private String marca;
        @JsonProperty("id")
        private String id;
        @JsonProperty("precioMax")
        private Double precioMax;
        @JsonProperty("precioMin")
        private Double precioMin;
        @JsonProperty("nombre")
        private String nombre;
        @JsonProperty("presentacion")
        private String presentacion;
        @JsonProperty("cantSucursalesDisponible")
        private Integer cantSucursalesDisponible;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("marca")
        public String getMarca() {
            return marca;
        }

        @JsonProperty("marca")
        public void setMarca(String marca) {
            this.marca = marca;
        }

        public Producto withMarca(String marca) {
            this.marca = marca;
            return this;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        public Producto withId(String id) {
            this.id = id;
            return this;
        }

        @JsonProperty("precioMax")
        public Double getPrecioMax() {
            return precioMax;
        }

        @JsonProperty("precioMax")
        public void setPrecioMax(Double precioMax) {
            this.precioMax = precioMax;
        }

        public Producto withPrecioMax(Double precioMax) {
            this.precioMax = precioMax;
            return this;
        }

        @JsonProperty("precioMin")
        public Double getPrecioMin() {
            return precioMin;
        }

        @JsonProperty("precioMin")
        public void setPrecioMin(Double precioMin) {
            this.precioMin = precioMin;
        }

        public Producto withPrecioMin(Double precioMin) {
            this.precioMin = precioMin;
            return this;
        }

        @JsonProperty("nombre")
        public String getNombre() {
            return nombre;
        }

        @JsonProperty("nombre")
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Producto withNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        @Override
        public String toString() {
            return String.format("EAN:%s - Nombre:%s",id,nombre);
        }
    }
}
