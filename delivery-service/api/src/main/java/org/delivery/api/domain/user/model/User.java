package org.delivery.api.domain.user.model;

import lombok.*;
import org.delivery.db.user.enums.UserStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode

/**
 * java - kotlin 연동작업 중..kotlin의 entity에서 java의 VO를 인식못하는 문제 발생
 * java의 lombok : build - lombok실행 - 파일생성 - getter, setter생성
 * kotlin은 컴파일 전이라 해당 부분을 인식을 못함
 * 방법 1. java에서 lombok anotation제거
 *         ㄴ @Data 삭제 후 getter, setter수동 생성,
 *         ㄴ @EqualsAndHashCode 추가
 *         (equals - (같은 객체인지 확인), hashCode - (객체 내부의 값이 같은지 확인) 자동 생성)
 * 방법 2. 클래스 자체를 kotlin으로 전환
 *
 */
public class User {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserStatus status;

    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDateTime getUnregisteredAt() {
        return unregisteredAt;
    }

    public void setUnregisteredAt(LocalDateTime unregisteredAt) {
        this.unregisteredAt = unregisteredAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
