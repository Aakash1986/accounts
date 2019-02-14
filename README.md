# accounts

# Post Request from postman
#localhost:8080/nordea/v1/accounts
#Parameter for post
{
    "account_name": "Brukskonto",
          "account_nickname": "Min Brukskonto",
          "account_owner_name": "Ola Nordmann",
          "account_type": "DEPOSIT",
          "currency": "NOK"
  }

  
# Post Response

{
    "account_number": 60010233341,
    "account_name": "Brukskonto",
    "account_nickname": "Min Brukskonto",
    "account_owner_name": "Ola Nordmann",
    "account_type": "DEPOSIT",
    "currency": "NOK",
    "available_balance": "0",
    "booked_balance": "0",
    "status": "Open"
}

# Get Request from postman
#localhost:8080/nordea/v1/accounts
# Get Response

{
    "accounts": [
        {
            "account_number": 60010011111,
            "account_name": "Brukskonto",
            "account_nickname": "Min Brukskonto",
            "account_owner_name": "Ola Nordmann",
            "account_type": "DEPOSIT",
            "currency": "NOK",
            "available_balance": "10000",
            "booked_balance": "8000",
            "status": "open"
        },
        {
            "account_number": 60010122222,
            "account_name": "Sparekonto",
            "account_nickname": "Min Sparekonto",
            "account_owner_name": "Ola Nordmann",
            "account_type": "SAVING",
            "currency": "NOK",
            "available_balance": "50000",
            "booked_balance": "50000",
            "status": "open"
        }
    ]
}


