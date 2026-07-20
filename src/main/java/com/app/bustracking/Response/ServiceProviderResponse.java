package com.app.bustracking.Response;

public record ServiceProviderResponse(
        Long id,
        String serviceprovidername,  // renamed
        String email,
        String mobile,
        String city,
        String state,
        String pincode
) {}