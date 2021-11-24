package com.vandai.mobi.jwt.security.payload.request;

import java.util.Set;

import lombok.Data;
@Data
public class SignupRequest {
	private String username;
	private String password;
	private Set<String> role;

}
//Nhìn vào hình trên chúng ta có thể thấy cách hoạt động của JWT như sau:
//
//User thực hiện login bằng cách gửi id/password hay 
//sử dụng các tài khoản mạng xã hội lên phía Authentication Server (Server xác thực)
//Authentication Server tiếp nhận các dữ liệu mà User gửi lên để phục vụ cho việc xác thực người dùng. 
//Trong trường hợp thành công, Authentication Server sẽ tạo một JWT và trả về cho người dùng thông qua 
//response.
//Người dùng nhận được JWT do Authentication Server 
//vừa mới trả về làm “chìa khóa” để thực hiện các “lệnh” tiếp theo đối với Application Server.
//Application Server trước khi thực hiện yêu cầu
//được gọi từ phía User, sẽ verify JWT gửi lên.
//Nếu OK, tiếp tục thực hiện yêu cầu được gọi.