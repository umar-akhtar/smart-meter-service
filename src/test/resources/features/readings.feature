Feature: Smart Meter Readings Feature

  Scenario: A customer requests smart meter readings
    Given the application is running
    Then a meter reading request is made with the account in the database and the customer can successfully see the readings

  Scenario: A customer requests smart meter readings but has no account
    Given the application is running
    Then a meter reading request is made with the account '123' and the customer can see a message stating account not found