public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Student(Long id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void validateFirstName() {
        if(this.firstName.isBlank()){
            throw  new RuntimeException("First name cannot be blank");
        }
    }

    public void validateLastName() {
        if(this.lastName.isBlank()){
            throw  new RuntimeException("Last name cannot be blank");
        }
    }


    public void validateEmail() {
        if (this.email.isBlank()) {
            throw new RuntimeException("Email cannot be blank");
        }

        // Regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!this.email.matches(emailRegex)) {
            throw new RuntimeException("Invalid email format");
        }
    }

    public void validatePhoneNumber() {
        if(this.phoneNumber.isBlank()){
            throw new RuntimeException("Phone number cannot be blank");
        }
        if(this.phoneNumber.length() !=10){
            throw new RuntimeException("Phone should be 10 number ");
        }

        if(!this.phoneNumber.matches("\\d+")){
            throw new RuntimeException("Phone number should contains only numbers ");
        }

        if(!this.phoneNumber.startsWith("0")){
            throw new RuntimeException("Phone number should start with 0");
        }



    }




}
