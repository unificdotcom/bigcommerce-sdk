package com.bigcommerce;

import com.bigcommerce.catalog.models.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class BigcommerceSdkDriver {

    private static final String STORE_HASH = System.getenv("BIGCOMMERCE_STORE_HASH");
    private static final String CLIENT_ID = System.getenv("BIGCOMMERCE_CLIENT_ID");
    private static final String ACCESS_TOKEN = System.getenv("BIGCOMMERCE_ACCESS_TOKEN");


    @Test
    public void givenPage1WhenRetrievingProductsThenReturnProducts() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Products actualProducts = bigcommerceSdk.getProducts(1);

        assertThat(actualProducts != null, Matchers.is(true));
        assertThat(actualProducts.getProducts().isEmpty(), Matchers.is(false));
        System.out.println("---- Products 1st Page ----");

    }

    @Test
    public void givenPage1WhenRetrievingOrdersThenReturnOrders() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final List<Order> actualOrders = bigcommerceSdk.getOrders(1);

        assertThat(actualOrders != null, Matchers.is(true));
        assertThat(actualOrders.isEmpty(), Matchers.is(false));


    }

    @Test
    public void givenNonZeroIdWhenRetrievingCustomerThenReturnCustomer() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Customer actualCustomer = bigcommerceSdk.getCustomer(1);

        assertThat(actualCustomer != null, Matchers.is(true));


    }

    @Test
    public void givenValidOrderIdWhenRetrievingShippingAddressThenReturnAtLeastOneShippingAddress() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Address address = bigcommerceSdk.getShippingAddress(114);

        assertThat(address != null, Matchers.is(true));


    }

    @Test
    public void givenValidOrderIdWhenRetrievingShipmentsThenReturnShipmentInformation() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final List<Shipment> shipments = bigcommerceSdk.getShipments(122, 1);

        assertThat(shipments != null, Matchers.is(true));
        assertThat(shipments.size() > 0, Matchers.is(true));

    }

   /* @Test
    public void givenValidStoreIdThenReturnStore() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Store store = bigcommerceSdk.getStore();

        assertNotNull(store);
        assertNotNull(store.getWeightUnits());

    }

    @Test
    public void givenShipmentRequestThenCreateShipment() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final ShipmentLineItem lineItem = new ShipmentLineItem();
        lineItem.setOrderProductId(1);
        lineItem.setQuantity(1);

        final ShipmentCreationRequest shipmentCreationRequest = ShipmentCreationRequest.newBuilder()
                .withTrackingNumber("1Z0398842038").withComments("This is a fulfillment from channel ape")
                .withOrderAddressId(1).withShippingProvider("UPS").withTrackingCarrier("")
                .withShipmentLineItems(Arrays.asList(lineItem)).build();

        final Shipment shipment = bigcommerceSdk.createShipment(shipmentCreationRequest, 100);

        assertNotNull(shipment);
        assertEquals(shipment.getTrackingNumber(), shipmentCreationRequest.getRequest().getTrackingNumber());
        assertEquals(shipment.getComments(), shipmentCreationRequest.getRequest().getComments());

    }

    @Test
    public void givenSomeProductWhenCreatingProductsThenCreateProduct() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Product product = new Product();
        product.setBrandId(0);
        product.setCategories(Arrays.asList(25));
        product.setCondition("New");
        product.setDescription("Custom description");
        product.setPrice(new BigDecimal(99.00));
        product.setMetaKeywords(Arrays.asList("Tetsing1", "testing2"));
        product.setIsConditionShown(true);
        product.setInventoryTracking("variant");
        product.setIsVisible(true);
        product.setName(UUID.randomUUID().toString());
        product.setSku(UUID.randomUUID().toString());
        product.setVariants(Collections.emptyList());
        product.setWeight(new BigDecimal(34.21));
        product.setType("physical");
        product.setCustomFields(Collections.emptyList());

        final Product createdProduct = bigcommerceSdk.createProduct(product);

        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getId());
        assertNotNull(createdProduct.getBrandId());
        assertEquals(product.getCondition(), createdProduct.getCondition());

    }

    @Test
    public void givenShipmentRequestThenUpdateShipment() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final ShipmentUpdateRequest shipmentUpdateRequest = ShipmentUpdateRequest.newBuilder()
                .withTrackingNumber("1Z0398842038").withComments("This is a fulfillment from channel ape")
                .withOrderAddressId(1).withShippingProvider("upsonline").withTrackingCarrier("").build();

        final Shipment shipment = bigcommerceSdk.updateShipment(shipmentUpdateRequest, 100, 36);

        assertNotNull(shipment);
        assertEquals(shipment.getTrackingNumber(), shipmentUpdateRequest.getRequest().getTrackingNumber());
        assertEquals(shipment.getComments(), shipmentUpdateRequest.getRequest().getComments());
    }

    @Test
    public void givenAnOrderIdThenCloseOrder() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Order order = bigcommerceSdk.completeOrder(100);
        assertNotNull(order);
        assertEquals(order.getStatus(), Status.COMPLETED);
    }

    @Test
    public void givenStatusNameThenGetOrderStatus() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final OrderStatus orderStatus = bigcommerceSdk.getStatus(Status.COMPLETED);
        assertNotNull(orderStatus);
        assertEquals(Status.COMPLETED.toString(), orderStatus.getName());

    }

    @Test
    public void givenPage1WhenRetrievingBrandsThenRetrieveBrands() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Brands brands = bigcommerceSdk.getBrands(1, 100);
        assertNotNull(brands);
        assertTrue(brands.getBrands().size() > 0);
        assertEquals(1, brands.getPagination().getCurrentPage());

    }

    @Test
    public void givenBrandWhenCreateBrandThenCreateBrand() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Brand expectedBrand = new Brand();
        expectedBrand.setName("CA_" + UUID.randomUUID().toString());
        expectedBrand.setImageUrl("https://www.channelape.com/wp-content/themes/ChannelApe/images/logo.png");
        expectedBrand.setMetaDescription("ECommerce Software");
        expectedBrand.setMetaKeywords(Arrays.asList("Ecommerce", "Channels"));
        expectedBrand.setSearchKeywords("ECOMMERCE,Channels");
        expectedBrand.setPageTitle("Channel Ape Ecommerce Software");
        CustomUrl customUrl = new CustomUrl();
        customUrl.setCustomized(true);
        customUrl.setUrl("/ca-foo");
        expectedBrand.setCustomUrl(customUrl);

        final Brand createdBrand = bigcommerceSdk.createBrand(expectedBrand);

        assertNotNull(createdBrand);
        assertNotNull(createdBrand.getId());
    }

    @Test
    public void givenAProductMetafieldAndProductIdWhenCreatingProductMetafieldThenCreateProductMetafield() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Metafield productMetafield = new Metafield();

        productMetafield.setKey(UUID.randomUUID().toString());
        productMetafield.setValue(UUID.randomUUID().toString());
        productMetafield.setNamespace("ChannelApe");
        productMetafield.setPermissionSet("write");
        productMetafield.setDescription("Testing Description");
        final Metafield metafield = bigcommerceSdk.createProductMetafield(112, productMetafield);
        assertNotNull(metafield);
        System.out.println("---- Created Metafield ----");
        System.out.println(new JSONObject(metafield).toString());

    }

    @Test
    public void givenAProductIdAndPage1WhenRetrievingProductMetafieldsThenReturnProductMetafields() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        final Metafields metafields = bigcommerceSdk.getProductMetafields(1960, 1, 250);
        assertNotNull(metafields);
        assertTrue(metafields.getMetafields().size() > 0);
        assertEquals(1, metafields.getPagination().getCurrentPage());
        System.out.println("---- Metafields 1st Page ----");
        System.out.println(new JSONObject(metafields).toString());

    }

    @Test
    public void givenAProductImageIdWhenDeletingProductImages() {
        final BigcommerceSdk bigcommerceSdk = BigcommerceSdk.newBuilder().withStoreHash(STORE_HASH)
                .withClientId(CLIENT_ID).withAccessToken(ACCESS_TOKEN).build();

        bigcommerceSdk.deleteProductImage(112, 381);

    }
*/
}
