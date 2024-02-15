package airline.ticket.booking;

public class Customer {
    private String id;
    private String name;
    private String phone;
    private String gender;
    private int age;

public Customer() {
    }

    public Customer(String id, String name, String phone, String gender, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name: "+name+"\n" +
                "id: "+id+"\n" +
                "phone: "+phone+"\n" +
                "gender: "+gender+"\n" +
                "age: "+age+"\n";
    }
}
