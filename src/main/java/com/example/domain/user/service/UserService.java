package com.example.domain.user.service;

import com.example.domain.user.model.MUser;
import java.util.List;

public interface UserService {

  /** ユーザー登録 */
  void signup(MUser user);

  /** ユーザー取得 */
  List<MUser> getUsers(MUser user);

  /** ユーザー取得(１件) */
  MUser getUserOne(String userId);

  /** ユーザー更新(１件) **/
  void updateUserOne(String userid, String password, String userName);

  /**　ユーザー削除(１件) */
  void deleteUserOne(String userid);

  MUser getLoginUser(String userId);
}
