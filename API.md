#	Summary	Type	Epic	Description	Priority	Points	Role Access	Assignee
1	[EPIC] Authentication & Authorization	Epic	AUTH	Xác thực và phân quyền người dùng	High	-	All	
2	[BE] POST /api/v1/auth/login	Story	AUTH	Đăng nhập, validate USER table, check is_active, trả JWT với role và privileges	High	3	Any User	
3	[BE] POST /api/v1/auth/logout	Story	AUTH	Đăng xuất, invalidate JWT token	Medium	1	Any User	
4	[BE] GET /api/v1/users/me	Story	AUTH	Lấy thông tin profile user hiện tại từ JWT	Medium	2	Any User	
5	[BE] GET /api/v1/users	Story	AUTH	Danh sách users, hỗ trợ pagination và filter	Medium	3	Admin	
6	[BE] POST /api/v1/users	Story	AUTH	Tạo user mới, validate required fields, hash password	High	3	Admin	
7	[BE] PUT /api/v1/users/{id}	Story	AUTH	Cập nhật thông tin user (trừ password)	Medium	2	Admin	
8	[BE] DELETE /api/v1/users/{id}	Story	AUTH	Deactivate user (set is_active = false)	Medium	2	Admin	
9	[BE] GET /api/v1/roles	Story	AUTH	Danh sách roles với privileges	Medium	2	Admin	
10	[BE] POST /api/v1/roles	Story	AUTH	Tạo role mới với privileges	Medium	3	Admin	
11	[BE] PUT /api/v1/roles/{id}	Story	AUTH	Cập nhật role name và privileges	Medium	2	Admin	
12	[EPIC] Product & Recipe Management	Epic	PRODUCT	Quản lý sản phẩm, nguyên liệu và công thức	High	-	Admin	
13	[BE] GET /api/v1/categories	Story	PRODUCT	Danh sách categories, filter by is_active	Low	2	Public	
14	[BE] POST /api/v1/categories	Story	PRODUCT	Tạo category mới, validate unique name	Medium	2	Admin	
15	[BE] PUT /api/v1/categories/{id}	Story	PRODUCT	Cập nhật category	Medium	1	Admin	
16	[BE] DELETE /api/v1/categories/{id}	Story	PRODUCT	Soft delete category, check liên kết products	Medium	2	Admin	
17	[BE] GET /api/v1/products	Story	PRODUCT	Danh sách sản phẩm (catalog), filter category/is_active/type, pagination	High	3	Public	
18	[BE] POST /api/v1/products	Story	PRODUCT	Tạo product: name, type (Raw/Processed), unit, price, category	High	3	Admin	
19	[BE] PUT /api/v1/products/{id}	Story	PRODUCT	Cập nhật thông tin product	Medium	2	Admin	
20	[BE] DELETE /api/v1/products/{id}	Story	PRODUCT	Deactivate product (set is_active = false)	Medium	2	Admin	
21	[BE] GET /api/v1/materials	Story	PRODUCT	Danh sách nguyên liệu thô (raw materials)	Medium	2	Admin	
22	[BE] POST /api/v1/materials	Story	PRODUCT	Tạo material mới dùng trong recipes	Medium	3	Admin	
23	[BE] PUT /api/v1/materials/{id}	Story	PRODUCT	Cập nhật material	Medium	2	Admin	
24	[BE] GET /api/v1/recipes	Story	PRODUCT	Danh sách recipes với thông tin product	Medium	3	Admin	
25	[BE] POST /api/v1/recipes	Story	PRODUCT	Tạo recipe với RECIPE_DETAIL (materials + quantity_needed)	High	5	Admin	
26	[BE] PUT /api/v1/recipes/{id}	Story	PRODUCT	Cập nhật recipe và ingredients	Medium	3	Admin	
27	[BE] GET /api/v1/recipes/{id}	Story	PRODUCT	Chi tiết recipe với danh sách materials	Medium	2	Admin	
28	[EPIC] Franchise Store Ordering	Epic	ORDER	Manager đặt hàng từ Central Kitchen	High	-	Manager	
29	[BE] GET /api/v1/stores/{storeId}/orders	Story	ORDER	Danh sách orders của store, filter by status, pagination	High	3	Manager	
30	[BE] POST /api/v1/stores/{storeId}/orders	Story	ORDER	Tạo STORE_ORDER với ORDER_DETAIL items	High	5	Manager	
31	[BE] GET /api/v1/stores/{storeId}/orders/{orderId}	Story	ORDER	Chi tiết order với line items và status	Medium	2	Manager	
32	[BE] PUT /api/v1/stores/{storeId}/orders/{orderId}	Story	ORDER	Sửa order - chỉ khi status = Draft (BR-07)	Medium	3	Manager	
33	[BE] DELETE /api/v1/stores/{storeId}/orders/{orderId}	Story	ORDER	Hủy order - chỉ khi status = Draft	Medium	2	Manager	
34	[BE] POST /api/v1/stores/{storeId}/orders/{orderId}/submit	Story	ORDER	Submit order: Draft -> Submitted, validate minimum value (BR-01)	High	3	Manager	
35	[EPIC] Kitchen Order Processing	Epic	KITCHEN	Coordinator xử lý orders từ các Store	High	-	Coordinator	
36	[BE] GET /api/v1/kitchen/orders	Story	KITCHEN	Danh sách pending orders, filter: Submitted, Processing	High	3	Coordinator	
37	[BE] GET /api/v1/kitchen/orders/{orderId}	Story	KITCHEN	Chi tiết order với thông tin store	Medium	2	Coordinator	
38	[BE] POST /api/v1/kitchen/orders/{orderId}/approve	Story	KITCHEN	Approve order, tạo INVOICE, check stock (BR-03)	High	5	Coordinator	
39	[BE] POST /api/v1/kitchen/orders/{orderId}/reject	Story	KITCHEN	Reject order với lý do, update status	Medium	2	Coordinator	
40	[BE] GET /api/v1/invoices	Story	KITCHEN	Danh sách invoices, filter by store, date range	Medium	3	Coordinator	
41	[BE] GET /api/v1/invoices/{id}	Story	KITCHEN	Chi tiết invoice với order details và amounts	Medium	2	Admin/Coordinator/Manager	
42	[EPIC] Shipment Management	Epic	SHIPMENT	Quản lý vận chuyển hàng đến Store	Medium	-	Coordinator/Kitchen Staff	
43	[BE] GET /api/v1/shipments	Story	SHIPMENT	Danh sách shipments, filter by status, date	Medium	3	Coordinator	
44	[BE] POST /api/v1/shipments	Story	SHIPMENT	Tạo shipment cho approved order/invoice	High	3	Coordinator	
45	[BE] PUT /api/v1/shipments/{id}/dispatch	Story	SHIPMENT	Dispatch shipment: status -> In Transit	Medium	2	Kitchen Staff	
46	[BE] POST /api/v1/stores/{storeId}/shipments/{id}/confirm	Story	SHIPMENT	Store xác nhận nhận hàng, update STORE_STOCK_ITEM, order -> Completed	High	5	Manager/Store Staff	
47	[EPIC] Production Planning	Epic	PRODUCTION	Lập kế hoạch sản xuất tại Central Kitchen	High	-	Coordinator/Kitchen Staff	
48	[BE] GET /api/v1/production-plans	Story	PRODUCTION	Danh sách production plans, filter status, date	Medium	3	Coordinator	
49	[BE] POST /api/v1/production-plans	Story	PRODUCTION	Tạo plan với batch_code, linked recipes (BR-06)	High	5	Coordinator	
50	[BE] GET /api/v1/production-plans/{id}	Story	PRODUCTION	Chi tiết plan với recipes và quantities	Medium	2	Coordinator/Kitchen Staff	
51	[BE] PUT /api/v1/production-plans/{id}	Story	PRODUCTION	Sửa plan - chỉ khi status = Planned	Medium	3	Coordinator	
52	[BE] POST /api/v1/production-plans/{id}/start	Story	PRODUCTION	Bắt đầu sản xuất: Planned -> In Production	Medium	2	Kitchen Staff	
53	[BE] POST /api/v1/production-plans/{id}/complete	Story	PRODUCTION	Hoàn thành: trừ materials, cộng products vào KITCHEN_STOCK_ITEM	High	5	Kitchen Staff	
54	[EPIC] Inventory Management	Epic	INVENTORY	Quản lý kho Central Kitchen và Store	Medium	-	Admin/Kitchen Staff/Store Staff	
55	[BE] GET /api/v1/kitchen/inventory	Story	INVENTORY	Danh sách tất cả stock items trong kho	Medium	3	Admin/Kitchen Staff	
56	[BE] GET /api/v1/kitchen/inventory/materials	Story	INVENTORY	Stock nguyên liệu thô, filter warehouse, expiry	Medium	2	Admin/Kitchen Staff	
57	[BE] GET /api/v1/kitchen/inventory/products	Story	INVENTORY	Stock thành phẩm, filter warehouse	Medium	2	Admin/Kitchen Staff	
58	[BE] POST /api/v1/kitchen/inventory/adjust	Story	INVENTORY	Manual stock adjustment (audit/waste)	Medium	3	Kitchen Staff	
59	[BE] GET /api/v1/stores/{storeId}/inventory	Story	INVENTORY	Tồn kho của store (STORE_STOCK_ITEM)	Medium	2	Manager/Store Staff	
60	[EPIC] Billing & Finance	Epic	BILLING	Quản lý hóa đơn và công nợ	Medium	-	Admin/Manager	
61	[BE] GET /api/v1/stores/{storeId}/billing	Story	BILLING	Billing statements của store, filter status, period	Medium	3	Manager	
62	[BE] GET /api/v1/billing-statements	Story	BILLING	Tất cả billing statements, support filtering	Medium	3	Admin	
63	[BE] GET /api/v1/billing-statements/{id}	Story	BILLING	Chi tiết billing statement với linked invoices	Medium	2	Admin/Manager	
64	[BE] POST /api/v1/billing-statements/{id}/confirm-payment	Story	BILLING	Xác nhận thanh toán, update paid_at, status, payment_method	High	3	Admin	
65	[BE] POST /api/v1/billing/generate-monthly	Story	BILLING	Tạo billing statement hàng tháng (BR-05), aggregate invoices	High	5	System/Admin	
