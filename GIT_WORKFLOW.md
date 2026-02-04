# 🚀 Hướng Dẫn Làm Việc Với Git - Team Workflow

## 📋 Mục Lục

- [Cấu Trúc Nhánh](#-cấu-trúc-nhánh)
- [Quy Trình Làm Việc](#-quy-trình-làm-việc)
- [Quy Tắc Đặt Tên](#-quy-tắc-đặt-tên)
- [Các Lệnh Git Thường Dùng](#-các-lệnh-git-thường-dùng)
- [Quy Trình Merge Code](#-quy-trình-merge-code)
- [Quy Tắc Push Code & Tạo Merge Request](#-quy-tắc-push-code--tạo-merge-request)
- [Xử Lý Conflict](#-xử-lý-conflict)
- [Những Điều Không Nên Làm](#-những-điều-không-nên-làm)

---

## 🌳 Cấu Trúc Nhánh

```
main (production)
  │
  └── develop (development)
        │
        ├── feature/xxx
        ├── bugfix/xxx
        └── hotfix/xxx
```

| Nhánh       | Mô Tả                                        | Ai Được Merge      |
| ----------- | -------------------------------------------- | ------------------ |
| `main`      | Nhánh chính, chứa code production ổn định    | Team Lead          |
| `develop`   | Nhánh phát triển, tích hợp các tính năng mới | Team Lead / Senior |
| `feature/*` | Nhánh phát triển tính năng mới               | Developer          |
| `bugfix/*`  | Nhánh sửa lỗi từ develop                     | Developer          |
| `hotfix/*`  | Nhánh sửa lỗi khẩn cấp từ main               | Developer          |

---

## 🔄 Quy Trình Làm Việc

### 1. Bắt Đầu Tính Năng Mới

```bash
# 1. Cập nhật develop mới nhất
git checkout develop
git pull origin develop

# 2. Tạo nhánh feature mới
git checkout -b feature/ten-tinh-nang

# 3. Làm việc và commit thường xuyên
git add .
git commit -m "feat: mô tả công việc"

# 4. Push lên remote
git push origin feature/ten-tinh-nang
```

### 2. Hoàn Thành Tính Năng

```bash
# 1. Cập nhật develop mới nhất vào nhánh feature
git checkout feature/ten-tinh-nang
git pull origin develop

# 2. Giải quyết conflict (nếu có)

# 3. Push code đã merge
git push origin feature/ten-tinh-nang

# 4. Tạo Pull Request trên GitHub/GitLab
```

### 3. Sửa Lỗi (Bugfix)

```bash
# 1. Tạo nhánh bugfix từ develop
git checkout develop
git pull origin develop
git checkout -b bugfix/ten-loi

# 2. Sửa lỗi và commit
git add .
git commit -m "fix: mô tả lỗi đã sửa"

# 3. Push và tạo Pull Request
git push origin bugfix/ten-loi
```

### 4. Hotfix (Lỗi Khẩn Cấp Trên Production)

```bash
# 1. Tạo nhánh hotfix từ main
git checkout main
git pull origin main
git checkout -b hotfix/ten-loi-khan-cap

# 2. Sửa lỗi và commit
git add .
git commit -m "hotfix: mô tả lỗi khẩn cấp"

# 3. Push và tạo Pull Request vào cả main VÀ develop
git push origin hotfix/ten-loi-khan-cap
```

---

## 📝 Quy Tắc Đặt Tên

### Tên Nhánh

```
<type>/<short-description>
```

**Ví dụ:**

- `feature/user-authentication`
- `feature/add-payment-gateway`
- `bugfix/fix-login-error`
- `hotfix/fix-critical-security-issue`

### Commit Message (Conventional Commits)

```
<type>: <description>

[optional body]
```

**Types:**
| Type | Mô Tả |
|------|-------|
| `feat` | Tính năng mới |
| `fix` | Sửa lỗi |
| `docs` | Thay đổi documentation |
| `style` | Format code (không ảnh hưởng logic) |
| `refactor` | Refactor code |
| `test` | Thêm/sửa test |
| `chore` | Công việc khác (cập nhật dependencies, config...) |

**Ví dụ:**

```bash
git commit -m "feat: thêm chức năng đăng nhập bằng Google"
git commit -m "fix: sửa lỗi không hiển thị avatar user"
git commit -m "docs: cập nhật README với hướng dẫn cài đặt"
git commit -m "refactor: tối ưu query lấy danh sách sản phẩm"
```

---

## 💻 Các Lệnh Git Thường Dùng

### Cơ Bản

```bash
# Clone repository
git clone <url>

# Xem trạng thái
git status

# Xem lịch sử commit
git log --oneline -10

# Xem các nhánh
git branch -a
```

### Làm Việc Với Nhánh

```bash
# Tạo và chuyển sang nhánh mới
git checkout -b <ten-nhanh>

# Chuyển sang nhánh khác
git checkout <ten-nhanh>

# Xóa nhánh local
git branch -d <ten-nhanh>

# Xóa nhánh remote
git push origin --delete <ten-nhanh>
```

### Commit & Push

```bash
# Thêm tất cả file thay đổi
git add .

# Thêm file cụ thể
git add <file-path>

# Commit với message
git commit -m "message"

# Push lên remote
git push origin <ten-nhanh>

# Force push (cẩn thận!)
git push origin <ten-nhanh> --force
```

### Cập Nhật Code

```bash
# Fetch tất cả thay đổi từ remote
git fetch --all

# Pull code mới nhất
git pull origin <ten-nhanh>

# Merge nhánh khác vào nhánh hiện tại
git merge <ten-nhanh>
```

### Hoàn Tác

```bash
# Hoàn tác thay đổi chưa stage
git checkout -- <file>

# Hoàn tác tất cả thay đổi chưa stage
git checkout -- .

# Unstage file
git reset HEAD <file>

# Hoàn tác commit gần nhất (giữ lại thay đổi)
git reset --soft HEAD~1

# Hoàn tác commit gần nhất (xóa thay đổi)
git reset --hard HEAD~1
```

---

## 🔀 Quy Trình Merge Code

### Sử Dụng Pull Request (Khuyến Nghị)

1. **Tạo Pull Request** trên GitHub/GitLab
2. **Assign Reviewer** để review code
3. **Reviewer kiểm tra:**
   - Code đúng convention
   - Không có lỗi logic
   - Test pass
4. **Approve & Merge** bởi người có quyền
5. **Xóa nhánh** sau khi merge

### Quy Tắc Review

- ✅ Code clean, dễ đọc
- ✅ Không có code thừa/comment không cần thiết
- ✅ Đặt tên biến/hàm có ý nghĩa
- ✅ Xử lý exception đầy đủ
- ✅ Test cases cover các trường hợp quan trọng

---

## 📤 Quy Tắc Push Code & Tạo Merge Request

### Trước Khi Push Code

#### ✅ Checklist Bắt Buộc

```
□ Code đã chạy được trên local (build thành công)
□ Đã test các chức năng liên quan
□ Không có lỗi lint/warning nghiêm trọng
□ Đã pull code mới nhất từ develop
□ Đã resolve tất cả conflict (nếu có)
□ Commit message đúng convention
□ Không commit file không cần thiết (.env, .idea, node_modules, target/...)
```

#### Các Bước Push Code

```bash
# 1. Kiểm tra status
git status

# 2. Xem lại các thay đổi
git diff

# 3. Stage files cần thiết (KHÔNG dùng git add . nếu có file không cần thiết)
git add src/main/java/com/swp/ckms/...
# hoặc
git add .

# 4. Commit với message rõ ràng
git commit -m "feat: thêm API tạo đơn hàng mới"

# 5. Pull develop mới nhất trước khi push
git pull origin develop

# 6. Push lên remote
git push origin feature/ten-tinh-nang
```

### Tạo Merge Request (MR) / Pull Request (PR)

#### Quy Tắc Đặt Tên MR/PR

```
[TYPE] Mô tả ngắn gọn

Ví dụ:
[FEAT] Thêm chức năng đăng nhập bằng Google
[FIX] Sửa lỗi không load được danh sách sản phẩm
[REFACTOR] Tối ưu query lấy thông tin user
[HOTFIX] Sửa lỗi bảo mật trong xác thực token
```

#### Template Mô Tả MR/PR

```markdown
## 📝 Mô Tả

- Mô tả ngắn gọn những gì đã làm

## 🔗 Link Task/Issue

- Link đến task trên Jira/Trello/GitHub Issues

## 📸 Screenshots (nếu có thay đổi UI)

- Đính kèm ảnh/video demo

## ✅ Checklist

- [ ] Code đã test trên local
- [ ] Không có lỗi build
- [ ] Đã viết unit test (nếu cần)
- [ ] Đã cập nhật documentation (nếu cần)

## 🧪 Cách Test

1. Bước 1...
2. Bước 2...

## ⚠️ Lưu Ý (nếu có)

- Các lưu ý quan trọng cho reviewer
```

#### Quy Định Khi Tạo MR/PR

| Quy Định          | Chi Tiết                                                |
| ----------------- | ------------------------------------------------------- |
| **Target Branch** | `develop` (mặc định), `main` (chỉ hotfix)               |
| **Assignee**      | Assign cho chính mình                                   |
| **Reviewer**      | Chọn ít nhất 1 người review (Senior/Lead)               |
| **Labels**        | Gắn label phù hợp: `feature`, `bug`, `hotfix`, `urgent` |
| **Milestone**     | Gắn vào Sprint/Milestone hiện tại                       |

#### Quy Trình Sau Khi Tạo MR/PR

```
1. 📢 Thông báo trên group chat rằng đã tạo MR
2. ⏳ Chờ reviewer review (tối đa 24h)
3. 💬 Phản hồi các comment từ reviewer
4. 🔄 Sửa code theo yêu cầu (nếu có)
5. ✅ Chờ approve từ reviewer
6. 🔀 Merge sau khi được approve (bởi Lead hoặc người có quyền)
7. 🗑️ Xóa nhánh sau khi merge
```

#### ⚠️ Lưu Ý Quan Trọng

> **KHÔNG** được tự merge MR/PR mà chưa có approve từ reviewer!

> **KHÔNG** được merge khi CI/CD pipeline fail!

> Nếu MR/PR có conflict, **PHẢI** resolve conflict trước khi yêu cầu review!

### Quy Tắc Review Code

#### Người Tạo MR/PR (Author)

- ✅ Đảm bảo code sạch, dễ đọc trước khi request review
- ✅ Phản hồi comment trong vòng 24h
- ✅ Không argue về style nếu không ảnh hưởng logic
- ✅ Cảm ơn reviewer sau khi được approve 🙏

#### Người Review (Reviewer)

- ✅ Review trong vòng 24h sau khi được assign
- ✅ Comment constructive, không chỉ trích cá nhân
- ✅ Đánh dấu rõ ràng: `[MUST FIX]`, `[SUGGESTION]`, `[QUESTION]`
- ✅ Approve ngay khi thấy code OK, không delay

---

## ⚠️ Xử Lý Conflict

### Khi Gặp Conflict

```bash
# 1. Pull code mới nhất từ develop
git pull origin develop

# 2. Git sẽ báo conflict, mở file có conflict
# Tìm và sửa các đoạn:
<<<<<<< HEAD
# Code của bạn
=======
# Code từ develop
>>>>>>> develop

# 3. Sau khi sửa, thêm và commit
git add .
git commit -m "resolve: merge conflict with develop"

# 4. Push lại
git push origin <ten-nhanh>
```

### Tips Tránh Conflict

- 📌 **Pull develop thường xuyên** vào nhánh feature của bạn
- 📌 **Commit nhỏ, thường xuyên** thay vì commit lớn
- 📌 **Communicate với team** khi làm việc trên cùng file
- 📌 **Chia task rõ ràng** để tránh overlap

---

## 🚫 Những Điều KHÔNG Nên Làm

| ❌ Không Làm                          | ✅ Nên Làm                   |
| ------------------------------------- | ---------------------------- |
| Push trực tiếp vào `main`             | Tạo PR và merge              |
| Force push vào nhánh chung            | Chỉ force push nhánh cá nhân |
| Commit message không rõ ràng          | Sử dụng conventional commits |
| Commit quá nhiều file không liên quan | Commit theo từng feature nhỏ |
| Merge khi chưa được review            | Chờ approve từ reviewer      |

---

## 📞 Liên Hệ Hỗ Trợ

Nếu gặp vấn đề với Git, liên hệ:

- **Team Lead**: Nguyễn Minh Tuấn

---

> 💡 **Tip**: Bookmark file này để tham khảo nhanh khi cần!
