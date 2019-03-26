package com.example.ramanbindal.panakeia;

/**
 * Created by Raman Bindal on 03-Nov-16.
 */

public class request {

    public String type;
    public String service;
    public String amount;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public request() {
    }

    public request(String type,String service,String amount) {
        this.type = type;
        this.service = service;
        this.amount = amount;

    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        request request = (request) o;
//
//        if (type != null ? !type.equals(request.type) : request.type != null) return false;
//        if (service != null ? !service.equals(request.service) : request.service != null)
//            return false;
//        return amount != null ? amount.equals(request.amount) : request.amount == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = type != null ? type.hashCode() : 0;
//        result = 31 * result + (service != null ? service.hashCode() : 0);
//        result = 31 * result + (amount != null ? amount.hashCode() : 0);
//        return result;
//    }
}
