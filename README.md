## Câu 1.**Tại sao Multi-stage Build là tiêu chuẩn Production?**

"Câu 1 (Docker & Multi-stage): Tại sao việc sử dụng Multi-stage build lại là tiêu chuẩn bắt buộc khi đóng gói container trên môi trường Production? So sánh sự khác biệt về dung lượng và bảo mật so với Dockerfile đơn tầng thông thường."


Multi-stage Build tách quá trình build và chạy ứng dụng thành hai giai đoạn riêng biệt. 
**Ưu điểm:** 
- Giảm kích thước Docker Image. 
-  Không chứa Maven, source code và các file build không cần thiết. 
-  Tăng tính bảo mật. 
-  Khởi động container nhanh hơn. 
-  Tiết kiệm tài nguyên lưu trữ và băng thông.
**So sánh** 

| Dockerfile đơn tầng    | Multi-stage Build  |
| ---------------------- | ------------------ |
| Image lớn              | Image nhỏ          |
| Chứa maven và src code | Chỉ chứa file jar  |
| Bảo mật thấp hơ n      | Bảo mật cao hơn    |
| Tốn tài nguyên         | Tối ưu tài nghuyên |
|                        |                    |

# Câu 2 service Scaling 

"Câu 2 (Service Scaling): Trong môi trường Docker Compose, nếu muốn mở rộng (scale-out) order-service lên 3 containers (docker compose up --scale order-service=3), hệ thống Gateway cần cấu hình thêm gì để phân tải (Load Balancing) request đến 3 containers này?
"

Nếu scale Order service lên 3 container , gateway cần hỗ trợ load balancing để phân phối request đến các instance của order-service 
	ta có thể sử dụng :
	 - Spring Cloud LoadBalancer - Service Discovery (Eureka, Consul...) 
	 - Hoặc Docker DNS kết hợp Gateway để cân bằng tải. 

Nhờ đó request sẽ được phân phối đến các container thay vì chỉ gửi đến một instance duy nhất.

## Câu 3. CI/CD Failure & Rollback
"Câu 3 (CI/CD Failure & Rollback): Trong quy trình CI/CD thực tế, nếu giai đoạn test hoặc docker-build bị lỗi (Failed), pipeline sẽ xử lý như thế nào và cơ chế đảm bảo an toàn cho môi trường Production ra sao?"


Nếu Job **Build**, **Test** hoặc **Docker Build** thất bại: 
- Pipeline sẽ dừng ngay. 
- - Các Job phía sau sẽ không được thực hiện. 
- - Docker Image sẽ không được push. 
- - Production sẽ không bị cập nhật.
- Điều này giúp đảm bảo chỉ những phiên bản đã được kiểm thử thành công mới được triển khai, giảm rủi ro và đảm bảo tính ổn định của hệ thống.

