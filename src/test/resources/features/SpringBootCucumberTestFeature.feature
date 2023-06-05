Feature: Active API functionalities

  Scenario: New user able to register and login
    When User registers a unique account
    Then An account is created
    When User log in with credentials
    Then The user is logged in

  Scenario: User able to create and update post
    When I create a new post
    Then The post is created
    When I update any of my posts
    Then The post is updated

  Scenario: User able to get random activity
    When I search for a random post
    Then The I receive a random post