
package com.qiniu.model;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Result_ {

    private Integer label;
    private Double score;
    private Boolean review;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(label).append(score).append(review).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result_) == false) {
            return false;
        }
        Result_ rhs = ((Result_) other);
        return new EqualsBuilder().append(label, rhs.label).append(score, rhs.score).append(review, rhs.review).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
