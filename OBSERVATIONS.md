# JPA Lifecycle Observations

## repository.save()

- Worked for new entities with null IDs
- Also handled initialized IDs
- Behaved similarly to merge()

## entityManager.persist()

- Worked for transient entities
- Required active transaction
- Failed for manually initialized IDs because persist() expects new entities

## entityManager.merge()

- Worked with initialized IDs
- Returned managed entity copy
- Handled detached entities better

## Parent-Child Relationship

- Parent with children persisted successfully
- Child without parent also persisted
- Saving child with transient parent required parent to be saved first

## Dirty Checking

- Updating managed entity without explicit save() automatically propagated changes after flush()

## Transactions

- persist() and merge() required transactional context
- EntityManager operations failed outside transactions

## Query Logging

- Hibernate generated INSERT and UPDATE SQL automatically
- Logs helped understand entity lifecycle internally

## Overall Understanding

- Entity lifecycle states (transient, managed, detached) affect JPA behavior
- persist(), merge(), and save() behave differently depending on entity state