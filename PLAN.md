# FlowOps Build Plan

## 1. Muc Tieu He Thong

FlowOps la he thong gom cac luong Ads, Sales va Warehouse vao mot quy trinh van hanh chung:

```text
ADS / SALES / WAREHOUSE
        |
      ORDER
        |
     PACKING
        |
     SHIPPING
        |
     DELIVERY
        |
   REVENUE / ROI
```

Muc tieu chinh:

- Quan ly nguon don hang tu Shopee, TikTok Shop, Lazada.
- Quan ly chi phi va hieu qua quang cao tu Facebook, TikTok, Google.
- Quan ly ton kho, nhap kho, xuat kho.
- Theo doi don hang tu luc tao don den giao hang.
- Tong hop doanh thu, chi phi ads, ROI va hieu qua van hanh.

## 2. Kien Truc API

Tat ca API dung prefix chung:

```text
/api/v1
```

Prefix nay duoc cau hinh trong:

```text
src/main/java/com/example/flowops/config/WebConfig.java
```

Quy uoc endpoint:

```text
/api/v1/{module}/{resource}
```

Vi du:

```text
GET  /api/v1/orders
GET  /api/v1/warehouse/inventory
POST /api/v1/sales/shopee/sync
GET  /api/v1/reports/roi
```

## 3. Cau Truc Package De Xuat

```text
com.example.flowops
├── config
├── common
├── ads
│   ├── AdsController
│   ├── AdsService
│   ├── AdsRepository
│   └── dto
├── sales
│   ├── SalesController
│   ├── SalesService
│   ├── SalesRepository
│   └── dto
├── warehouse
│   ├── WarehouseController
│   ├── WarehouseService
│   ├── WarehouseRepository
│   └── dto
├── order
│   ├── OrderController
│   ├── OrderService
│   ├── OrderRepository
│   └── dto
├── packing
├── shipping
├── delivery
└── report
```

Quy uoc moi module:

- `Controller`: nhan request va tra response.
- `Service`: xu ly nghiep vu.
- `Repository`: truy van database.
- `dto`: object request/response.
- `entity`: model anh xa database, co the dat trong module hoac package `domain`.

## 4. Module Ads

Nguon:

- Facebook Ads
- TikTok Ads
- Google Ads

Chuc nang:

- Luu campaign.
- Luu chi phi quang cao.
- Luu conversion neu co.
- Dong bo du lieu ads tu tung nen tang.
- Cung cap du lieu tinh ROI.

API de xuat:

```text
GET  /api/v1/ads/campaigns
GET  /api/v1/ads/spend
POST /api/v1/ads/facebook/sync
POST /api/v1/ads/tiktok/sync
POST /api/v1/ads/google/sync
```

Bang du lieu de xuat:

```text
ads_campaigns
ads_spend_logs
ads_platform_accounts
```

## 5. Module Sales

Nguon:

- Shopee
- TikTok Shop
- Lazada

Chuc nang:

- Dong bo don hang tu san.
- Chuan hoa don hang ve format chung.
- Day don hang vao module Order.
- Theo doi kenh ban tao ra don.

API de xuat:

```text
GET  /api/v1/sales/orders
POST /api/v1/sales/shopee/sync
POST /api/v1/sales/tiktok/sync
POST /api/v1/sales/lazada/sync
```

Bang du lieu de xuat:

```text
sales_channels
external_orders
external_order_items
```

## 6. Module Warehouse

Chuc nang:

- Quan ly san pham.
- Quan ly ton kho.
- Nhap kho.
- Xuat kho.
- Tru ton khi don duoc xac nhan hoac dong goi.

API de xuat:

```text
GET  /api/v1/warehouse/products
POST /api/v1/warehouse/products
GET  /api/v1/warehouse/inventory
GET  /api/v1/warehouse/products/{id}/stock
POST /api/v1/warehouse/imports
POST /api/v1/warehouse/exports
```

Bang du lieu de xuat:

```text
products
inventory
stock_movements
warehouse_imports
warehouse_exports
```

## 7. Module Order

Order la trung tam cua he thong.

Trang thai de xuat:

```text
NEW
CONFIRMED
PACKING
PACKED
SHIPPING
DELIVERED
FAILED
CANCELLED
```

API de xuat:

```text
GET    /api/v1/orders
POST   /api/v1/orders
GET    /api/v1/orders/{id}
PATCH  /api/v1/orders/{id}/status
DELETE /api/v1/orders/{id}
```

Bang du lieu de xuat:

```text
orders
order_items
order_status_logs
```

## 8. Module Packing

Chuc nang:

- Tao task dong goi.
- Gan don cho nhan vien dong goi.
- Xac nhan dong goi xong.
- Tru ton kho neu chua tru o buoc order.

API de xuat:

```text
GET   /api/v1/packing/tasks
POST  /api/v1/packing/tasks
PATCH /api/v1/packing/tasks/{id}/packed
```

Bang du lieu de xuat:

```text
packing_tasks
packing_logs
```

## 9. Module Shipping

Chuc nang:

- Tao van don.
- Gan don vi van chuyen.
- Theo doi trang thai van chuyen.

API de xuat:

```text
GET   /api/v1/shipping/shipments
POST  /api/v1/shipping/shipments
GET   /api/v1/shipping/shipments/{id}
PATCH /api/v1/shipping/shipments/{id}/status
```

Bang du lieu de xuat:

```text
shipments
shipping_providers
shipping_status_logs
```

## 10. Module Delivery

Chuc nang:

- Ghi nhan giao thanh cong.
- Ghi nhan giao that bai.
- Cap nhat doanh thu thuc nhan.

API de xuat:

```text
GET   /api/v1/delivery/orders
PATCH /api/v1/delivery/orders/{id}/delivered
PATCH /api/v1/delivery/orders/{id}/failed
```

## 11. Module Report

Chuc nang:

- Tong doanh thu.
- Tong chi phi ads.
- So don theo trang thai.
- ROI theo ngay, kenh ban, campaign.
- Dashboard tong quan.

API de xuat:

```text
GET /api/v1/reports/dashboard
GET /api/v1/reports/revenue
GET /api/v1/reports/roi
GET /api/v1/reports/orders
```

Response dashboard vi du:

```json
{
  "revenue": 120000000,
  "adSpend": 30000000,
  "orders": 850,
  "deliveredOrders": 720,
  "roi": 4.0
}
```

## 12. Luong Xu Ly Chinh

### 12.1 Dong Bo Don Hang

```text
Shopee/TikTok/Lazada
        |
Sales Sync API
        |
external_orders
        |
orders
        |
PACKING
```

### 12.2 Xu Ly Don Hang

```text
Order Created
     |
Check Inventory
     |
Packing Task
     |
Shipment Created
     |
Delivery Result
     |
Revenue Report
```

### 12.3 Tinh ROI

```text
Ads Spend + Delivered Revenue
              |
           ROI Report
```

Cong thuc co ban:

```text
ROI = revenue / ad_spend
```

Neu can tinh loi nhuan:

```text
profit = revenue - product_cost - shipping_cost - ad_spend
```

## 13. Thu Tu Xay Dung De Xuat

### Phase 1: Nen Tang API

- Cau hinh `/api/v1`.
- Tao response format chung.
- Tao exception handler chung.
- Tao cac package module.
- Tao health/home endpoint.

### Phase 2: Order Va Warehouse

- Tao product.
- Tao inventory.
- Tao order.
- Tao order item.
- Kiem tra ton kho khi tao don.

### Phase 3: Packing Va Shipping

- Tao packing task.
- Cap nhat trang thai dong goi.
- Tao shipment.
- Cap nhat trang thai van chuyen.

### Phase 4: Sales Channel

- Tao sales channel.
- Tao external order.
- Viet sync mock cho Shopee, TikTok, Lazada.
- Map external order vao order noi bo.

### Phase 5: Ads Va Report

- Tao campaign.
- Tao ads spend log.
- Tao dashboard report.
- Tinh revenue va ROI.

### Phase 6: Tich Hop That

- Ket noi API Shopee.
- Ket noi API TikTok Shop.
- Ket noi API Lazada.
- Ket noi Facebook/TikTok/Google Ads.

## 14. Viec Can Lam Tiep Theo

Danh sach viec nen lam ngay:

- Tao package `common` cho response va error.
- Tao package `order`, `warehouse`, `report`.
- Them database dependency: Spring Data JPA va PostgreSQL/MySQL.
- Tao entity dau tien: `Product`, `Inventory`, `Order`, `OrderItem`.
- Tao API CRUD co ban cho product va order.
- Them migration tool neu can: Flyway hoac Liquibase.

