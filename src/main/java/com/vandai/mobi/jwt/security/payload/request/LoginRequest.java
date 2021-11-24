package com.vandai.mobi.jwt.security.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;
}
//Đoạn mã JSON Web Token bao gồm 3 thành phần chính được ngăn cách với nhau bởi dấu (.) đó là:
//
//Header
//Payload
//Signature
//Do đó đoạn JWT của chúng ta sẽ có dạng như sau:
//
//xxxx.yyyy.zzzz
//Header
//Phần Header thường bao gồm 2 thành phần như sau:
//
//Loại token – mặc định sẽ  là JWT.
//Thuật toán của chữ ký đang được sử dụng ví dụ như HMAC, SHA256, RSA.
//Payload
//Thành phần thứ 2 của JWT đó là payload – thành phần này sẽ chứa nôi dung của thông tin (claim). 
//Thông tin có thể là mô tả của 1 thực thể ( thực thể user) hoặc cũng có thể là 
//những thông tin bổ sung thêm cho phần Header.
//Chúng được chia làm 3 loại thông tin là: registered, public, và private.