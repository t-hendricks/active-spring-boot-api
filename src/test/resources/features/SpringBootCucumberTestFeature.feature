Feature: Active API functionalities

  Scenario: User able to create and update post
    Given A list of my posts exists
    When I create a new post
    Then The post is created
    When I update any of my posts
    Then The post is updated