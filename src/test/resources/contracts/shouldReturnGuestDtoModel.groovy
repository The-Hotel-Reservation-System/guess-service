import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return guestDto model"
    request {
        method 'GET'
        url ("/api/guest/1")
    }
    response {
        body(
                id: 1,
                 name: "NAME",
                 email: "EMAIL",
                 phone: "PHONE"
        )
        headers {
            contentType applicationJson()
        }
        status 200
    }
}

