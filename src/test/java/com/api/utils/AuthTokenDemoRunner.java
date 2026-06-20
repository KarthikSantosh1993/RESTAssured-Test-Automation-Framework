package com.api.utils;

import java.io.IOException;

import com.api.constants.Role;

public class AuthTokenDemoRunner {

	public static void main(String[] args) throws IOException {
		for (int i = 0; i <= 100; i++) {
			String token = AuthTokenProvider.getToken(Role.FD);
			System.out.println(token);
		}
	}

}
