package com.example.bankbackend.customer;


class CustomerRole {
    static CustomerRole restore(CustomerRoleSnapshot snapshot){
        return new CustomerRole(
                snapshot.getId(),
                snapshot.getRole()
        );
    }

    private int id;
    private String role;

    CustomerRole(int id, String role) {
        this.id = id;
        this.role = role;
    }
    CustomerRole(String role) {
             this.role = role;
    }
    CustomerRoleSnapshot getSnapshot(){
        return new CustomerRoleSnapshot(id, role);
    }
}
