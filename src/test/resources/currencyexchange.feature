Feature: Currency Exchange Rate

  Scenario Outline: Verify conversion rate exposed correctly
    Given conversion rate for <fromcurrency> to <tocurrency>
    When the system is asked to provide the conversion rate
    Then It should output <output>

    Examples:
      |	fromcurrency	|	tocurrency	|	output		|
      |	USD	|	MXN	    |	21	|
      |	MXN	|	USD	    |	0.5	|