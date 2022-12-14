package model;

import java.time.Instant;

public class Order {
    private long id;
    private String name;
    private String phone;
    private String address;
    private double grandTotal;

    private long idUser;
    private Instant creatAt;

    private Instant updateAt;

    public Order() {
    }

    public Order(long id , long idUser, String name, String phone, String address , double grandTotal  , Instant creatAt , Instant updateAt) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.grandTotal = grandTotal;
        this.creatAt = creatAt;
        this.updateAt = updateAt;
    }
    public Order(long orderId, String name, String phone, String address, Instant creatAt, Instant updateAt) {
        this.id = orderId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.creatAt = creatAt;
        this.updateAt = updateAt;
    }

    public static Order parseOrder(String raw){
        //7577,0,0987654567,huế,0.0,Nguyễn Văn T,2022-12-09T09:22:11.282020800Z,2022-12-09T09:22:11.809798Z
        /*

        id + "," +
                userId+
                "," +
                phone +
                "," +
                address+
                "," +
                GrandTotal+
                "," +
                name +
                "," +
                creatAt+
                ","+
                updateAt;
         */
        Order order = new Order();
        String [] fields = raw.split(",");
        order.id = Long.parseLong(fields[0]);
        order.idUser = Long.parseLong(fields[1]);
        order.name = fields[5];
        order.phone =fields[2];
        order.address = fields[3];
        order.grandTotal = Double.parseDouble(fields[4]);
        order.creatAt = Instant.parse(fields[6]);
        String temp = fields[7];
        if(temp != null && !temp.equals("null"))
            order.updateAt = Instant.parse(temp);
        return order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Instant creatAt) {
        this.creatAt = creatAt;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    @Override
    public String toString() {
        return id + "," +
                idUser+
                "," +
                phone +
                "," +
                address+
                "," +
                grandTotal+
                "," +
                name +
                "," +
                creatAt+
                ","+
                updateAt;
    }
}
