CREATE TABLE order_system.order_details (
  orderId INT NOT NULL,
  orderQuantity varchar2 NULL,
  productId INT,
  orderStatus varchar2 NULL,
  orderedProduct varchar2 NULL,
  orderedBy varchar2 NULL,
  orderedDate Date,
  orderUpdatedDate Date,
  order_timestamp TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (orderId ));