package com.example.rest;

import com.example.domain.user.model.MUser;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;


@RestController
@RequestMapping("/user")
public class UserRestController {

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private MessageSource messageSource;

  /** ユーザーを登録 */
  @PostMapping("/signup/rest")
  public RestResult postSignup(@Validated(GroupOrder.class)
      SignupForm form, BindingResult bindingResult, Locale local) {

    // 入力チェック結果
    if (bindingResult.hasErrors()) {
      //入力チェック結果：NG
      Map<String, String> errors = new HashMap<>();

      // エラーメッセージ取得
      for (FieldError error : bindingResult.getFieldErrors()) {
        String message = messageSource.getMessage(error, local);
        errors.put(error.getField(), message);
      }
      // エラーの結果の返却
      return new RestResult(90, errors);
    }

    // formをMUserクラスに変換
    MUser user = modelMapper  .map(form, MUser.class);

    // ユーザー登録
    userService.signup(user);
    // 返却の結果
    return new RestResult(0, null);
  }

  /** ユーザーを更新 */
  @PutMapping("/update")
  public int updateUser(UserDetailForm form) {
    // ユーザーを更新
    userService.updateUserOne(form.getUserId(),
    form.getPassword(), form.getUserName());

    return 0;
  }
   /** ユーザーを削除 */
   @DeleteMapping("/delete")
  public int deleteUser(UserDetailForm form) {
     // ユーザーを削除
     userService.deleteUserOne(form.getUserId());

     return 0;
   }
}