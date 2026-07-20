package com.app.bustracking.Request;

public record ServiceProviderRequest(
        String serviceprovidername,  // renamed
        String email,
        String mobile,
        String city,
        String state,
        String pincode
) {}