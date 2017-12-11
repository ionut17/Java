package filters;

import javax.faces.bean.ManagedBean;

/**
 * Created by Ionut on 11-Dec-17.
 */
@ManagedBean(name="filter", eager = true)
public class Filter {

    private Boolean nameFilterActive;

    public Boolean getNameFilterActive() {
        return nameFilterActive;
    }

    public void setNameFilterActive(Boolean nameFilterActive) {
        this.nameFilterActive = nameFilterActive;
    }

    public Boolean getDescriptionFilterActive() {
        return descriptionFilterActive;
    }

    public void setDescriptionFilterActive(Boolean descriptionFilterActive) {
        this.descriptionFilterActive = descriptionFilterActive;
    }

    public Boolean getQuotaFilterActive() {
        return quotaFilterActive;
    }

    public void setQuotaFilterActive(Boolean quotaFilterActive) {
        this.quotaFilterActive = quotaFilterActive;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }

    public void setDescriptionValue(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getQuotaValue() {
        return quotaValue;
    }

    public void setQuotaValue(String quotaValue) {
        this.quotaValue = quotaValue;
    }

    private Boolean descriptionFilterActive;
    private Boolean quotaFilterActive;

    private String nameValue;
    private String descriptionValue;
    private String quotaValue;

}
