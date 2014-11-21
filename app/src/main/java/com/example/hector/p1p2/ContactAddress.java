package com.example.hector.p1p2;

public class ContactAddress
{
  private String city;
  private String name;
  private String number;
  private String state;
  private String street;
  private String zipCode;
  
  public ContactAddress()
  {
    this.name = "";
    this.street = "";
    this.number = "";
    this.city = "";
    this.state = "";
    this.zipCode = "";
  }
  
  public ContactAddress(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.name = paramString1;
    this.street = paramString2;
    this.number = paramString3;
    this.city = paramString4;
    this.state = paramString5;
    this.zipCode = paramString6;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getNumber()
  {
    return this.number;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public String getStreet()
  {
    return this.street;
  }
  
  public String getZip()
  {
    return this.zipCode;
  }
  
  public void setCity(String paramString)
  {
    this.city = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setNumber(String paramString)
  {
    this.number = paramString;
  }
  
  public void setState(String paramString)
  {
    this.state = paramString;
  }
  
  public void setStreet(String paramString)
  {
    this.street = paramString;
  }
  
  public void setZip(String paramString)
  {
    this.zipCode = paramString;
  }
}




/* Location:           C:\TUTORIAL\app\classes-dex2jar.jar

 * Qualified Name:     mycontacts2.icom4035.edu.uprm.ece.mycontacts2.ContactAddress

 * JD-Core Version:    0.7.1

 */