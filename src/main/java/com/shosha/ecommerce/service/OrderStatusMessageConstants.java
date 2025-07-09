package com.shosha.ecommerce.service;

public class OrderStatusMessageConstants {

    public static final String PENDING_ORDER_MESSAGE =
            " is currently pending. We will notify you once it's approved.";

    public static final String APPROVED_ORDER_MESSAGE = """
     has been approved and is being prepared for processing.
    Thank you for shopping with us!
    """;

    public static final String PROCESSING_ORDER_MESSAGE = """
     is now being processed and is scheduled for shipment within 24 hours.
    We appreciate your patience!
    """;

    public static final String SHIPPED_ORDER_MESSAGE =
            " has been shipped and is on its way to your address. Stay tuned for delivery updates!";

    public static final String CANCELED_ORDER_MESSAGE =
            " has been canceled. You can restore it if you want!";



    public static final String DEFAULT_ORDER_MESSAGE =
            "We are currently unable to determine the status of your order. Please ensure that your order number is correct or contact customer support for further assistance.";


}
