package ORDER;

public class OrderCreat {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private String []color;

    public OrderCreat(String firstName, String lastName, String address, String metroStation, String phone, String rentTime, String deliveryDate, String comment, String [] color){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    public OrderCreat() {
    }
    public String getFirstName (){
        return firstName;
    }
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName (){
        return lastName;
    }
    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getAddress (){
        return address;
    }
    public void setAddress (String address) {
        this.address = address;
    }

    public String getMetroStation (){
        return metroStation;
    }
    public void setMetroStation (String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone (){
        return phone;
    }
    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getRentTime (){
        return rentTime;
    }
    public void setRentTime (String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate (){
        return deliveryDate;
    }
    public void setDeliveryDate (String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment (){
        return comment;
    }
    public void setComment (String comment) {
        this.comment = comment;
    }

    public String [] getColor (){
        return color;
    }
    public void setColor (String [] color) {
        this.color = color;
    }

    public OrderCreat (String[] color) {
        this.firstName = "Котик";
        this.lastName = "Пушистый";
        this.address = "ул. Мягких лапок, 25";
        this.metroStation = "Сокольники";
        this.phone = "+7123456789";
        this.rentTime = "3";
        this.deliveryDate = "2022-12-20";
        this.comment = "коммент";
        this.color = color;
    }


}