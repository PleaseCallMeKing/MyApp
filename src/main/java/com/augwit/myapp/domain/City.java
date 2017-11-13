package com.augwit.myapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jhi_city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @NotNull
    @Column(name = "city_name")
    private String cityName;

    @NotNull
    @Column(name = "city_area")
    private Double cityArea;

    @NotNull
    @Column(name = "city_province")
    private String province;

    @NotNull
    @Column(name = "postal_code")
    private String postalCode;

    public City() {
    }


    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(String cityName, Double cityArea) {
        this.cityName = cityName;
        this.cityArea = cityArea;
    }

    public City(String cityName, Double cityArea, String province,String postalCode) {
        this.cityName = cityName;
        this.cityArea = cityArea;
        this.province = province;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getCityArea() {
        return cityArea;
    }

    public void setCityArea(Double cityArea) {
        this.cityArea = cityArea;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (cityId != null ? !cityId.equals(city.cityId) : city.cityId != null) return false;
        if (cityName != null ? !cityName.equals(city.cityName) : city.cityName != null) return false;
        if (cityArea != null ? !cityArea.equals(city.cityArea) : city.cityArea != null) return false;
        if (postalCode != null ? !postalCode.equals(city.postalCode) : city.postalCode !=null) return false;
        return province != null ? province.equals(city.province) : city.province == null;
    }

    @Override
    public int hashCode() {
        int result = cityId != null ? cityId.hashCode() : 0;
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (cityArea != null ? cityArea.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
            "cityId=" + cityId +
            ", cityName='" + cityName + '\'' +
            ", cityArea=" + cityArea +
            ", province='" + province + '\'' +
            ", postalCode='" + postalCode + '\'' +
            '}';
    }

}
