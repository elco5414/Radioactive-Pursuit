Feature: Game play with artifacts other than food
  As a game player
  I want to play a game with a artifacts in the maze
  So that I have a richer set of choices

  Scenario: Armored suits are available in the maze and worn by knights for extra protection during fights
    Given a maze with the following rooms:
      | Starting |
    And a knight named Galahad in the Starting room with a fixed die of 4
    And the Starting room contains the following artifacts:
      | Type  | Name    | Health Value | Moving Cost | Defense Value | Damage Value |
      | Armor | iron    | 0.0          | 0.3         | 2.0           | 0.0          |
      | Food  | cupcake | 1.0          | 0.0         | 0.0           | 0.0          |

    When the game plays a turn

    Then Galahad picked up the Armor
    And there is no armor in Starting room

    Given a demon named Satan in the Starting room with a fixed die of 6
    When the game plays a turn

    Then a fight has occurred
    # Despite losing the fight by 2, the armor prevents the loss
    # But both fight each other, so they both lose double the default fight cost
    And Galahad has lost 2 times the default fight cost in health
    And Satan has lost 2 times the default fight cost in health


  Scenario: Armored suits are heavy and incur extra health costs when moving
    Given a fully-connected maze with the following rooms:
      | Starting |
      | Other    |
    And a knight named Galahad in the Starting room
    And the Starting room contains the following artifacts:
      | Type  | Name | Health Value | Defense Value | Moving Cost | Damage Value |
      | Armor | iron | 0.0          | 2.0           | 0.3         | 0.0          |

    When the game plays a turn

    Then Galahad picked up the Armor
    And there is no armor in Starting room
    
    When the game plays a turn

    Then Galahad is in the Other room
    And Galahad lost the default-moving cost plus 0.3 in health


  Scenario: Knight wears two suits of armor to get even more protection
    Given a maze with the following rooms:
      | Starting |
    And a knight named Galahad in the Starting room with a fixed die of 1
    And the Starting room contains the following artifacts:
      | Type  | Name    | Health Value | Moving Cost | Defense Value | Damage Value |
      | Armor | iron    | 0.0          | 0.3         | 2.0           | 0.0          |
      | Armor | mithril | 0.0          | 0.1         | 3.0           | 0.0          |

    When the game plays a turn

    Then Galahad picked up the Armor

    When the game plays a turn
    # Now Galahad is wearing two suits of armor
    Then there is no armor in Starting room

    Given a demon named Satan in the Starting room with a fixed die of 6
    When the game plays a turn

    Then a fight has occurred
    # Despite losing the fight by 2, the armor prevents the loss
    # But both fight each other, so they both lose double the default fight cost
    And Galahad has lost 2 times the default fight cost in health
    And Satan has lost 2 times the default fight cost in health


    #now testing out the weapon functionality
  Scenario: Weapons are now available for Fighting and can be picked up by Knights which increases Damage to the opponent
    Given a maze with the following rooms:
      | Starting |
    And a knight named Joe in the Starting room with a fixed die of 6
    And the Starting room contains the following artifacts:
      | Type  | Name    | Health Value | Moving Cost | Defense Value | Damage Value |
      | Weapon | sword    | 0.0          | 0.0         | 0.0           | 2.0        |
      | Food  | cupcake | 1.0          | 0.0         | 0.0           | 0.0          |

    When the game plays a turn

    Then Joe picked up the Weapon
    And there is no Weapon in Starting room

    Given a demon named Monica in the Starting room with a fixed die of 5
    When the game plays a turn

    # they will fight twice, because joe fights monica, then monica fights joe
    # joes die = 6, monicas = 5
    # joe has a weapon with damage value 2
    # fight 1 -> monica loses 3.5
    # fight 2 -> monica loses 3.5
    And Monica has lost 7 points in health

    #test that if there is armor and weapon knight will pick up armor, then weapon and have both
  Scenario: Knight will pick up armor and then weapon, and have both
    Given a maze with the following rooms:
      | Starting |
    And a knight named Joe in the Starting room with a fixed die of 6
    And the Starting room contains the following artifacts:
      | Type  | Name    | Health Value | Moving Cost | Defense Value | Damage Value |
      | Weapon | sword    | 0.0          | 0.0         | 0.0           | 2.0        |
      | Food  | cupcake | 1.0          | 0.0         | 0.0           | 0.0          |
      | Armor  | iron | 0.0         | 0.5         | 2.0           | 0.0          |

    When the game plays a turn

    Then Joe picked up the Armor
    And there is no armor in Starting room

    When the game plays a turn

    Then Joe picked up the Weapon
    And there is no Weapon in Starting room

    Given a demon named Monica in the Starting room with a fixed die of 5
    When the game plays a turn

    Then a fight has occurred

    # they will fight twice, because joe fights monica, then monica fights joe
    # joes die = 6, monicas = 5
    # joe has a weapon with damage value 2
    # fight 1 -> monica loses 3.5
    # fight 2 -> monica loses 3.5
    And Monica has lost 7 points in health

    #joe will lose the default amount twice
    And Joe has lost 1 points in health
