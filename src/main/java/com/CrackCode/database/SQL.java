package com.CrackCode.database;

public final class SQL {
    private SQL() {
    };
    /**
     * For INNER JOIN between parent and child
     * (INNER) JOIN: Returns records that have matching values in both tables (here just match parent and child id)
     */
    public static final String getChildNationalityByParentIdentity
            = "SELECT parent.id, parent.name, parent.email,child.nationality as childNationality \n" +
            "FROM child INNER JOIN parent ON child.id=parent.id";
    /**
     * For LEFT JOIN between customer and order_table
     */
    public static final String getAllCustomerNameAndAllOrderAmount
            ="SELECT customer.name, order_table.amount as orderAmount" +
            " FROM customer LEFT JOIN order_table ON customer.id = order_table.id ORDER BY customer.name";


}
