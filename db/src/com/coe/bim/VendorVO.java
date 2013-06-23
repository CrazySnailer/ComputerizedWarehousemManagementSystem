package com.coe.bim;


public class VendorVO
{
    private int vendorId;
    private String vendorName;
    private String cityId;
    private String address;
    private String postcode;
    private String telphone;
    private String contact; 
    private String status;
    
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address=address;
    }
    public String getCityId()
    {
        return cityId;
    }
    public void setCityId(String cityId)
    {
        this.cityId=cityId;
    }
    public String getContact()
    {
        return contact;
    }
    public void setContact(String contact)
    {
        this.contact=contact;
    }
    public String getPostcode()
    {
        return postcode;
    }
    public void setPostcode(String postcode)
    {
        this.postcode=postcode;
    }
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status=status;
    }
    public String getTelphone()
    {
        return telphone;
    }
    public void setTelphone(String telphone)
    {
        this.telphone=telphone;
    }
    public String getVendorName()
    {
        return vendorName;
    }
    public void setVendorName(String vendorName)
    {
        this.vendorName=vendorName;
    }
    public int getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(int vendorId)
    {
        this.vendorId=vendorId;
    }
}
