Feature: Active API functionalities

  Scenario: New user able to register and login
    When User registers a unique account
    Then An account is created
#    When User log in with credentials
#    Then The user is logged in

#  Scenario: User able to create and update post
#    Given A list of my posts exists
#    When I create a new post
#    Then The post is created
#    When I update any of my posts
#    Then The post is updated