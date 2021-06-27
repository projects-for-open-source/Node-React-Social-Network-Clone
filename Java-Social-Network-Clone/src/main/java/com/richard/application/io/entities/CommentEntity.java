/**
 * @Nickname: Richard
 * @Email: richardvu.work@gmail.com
 * @Skype: richardvu.work@gmail.com
 * @Phone: (+84) 0935710974 - (+84) 0935810974
 * @Country: Viet Nam
 * @Year: 2021
 * @LICENSE: DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.io.entities;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mx-linux
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
// @Entity(name = "comments")
public class CommentEntity implements Serializable {

  /** serialVersionUID: */
  private static final long serialVersionUID = 1L;

  /** commentID: */
  // @PrimaryKey
  private UUID commentID;

  /** postID: */
  private UUID postID;

  /** userID: */
  private String userID;

  /** userImage: */
  private String userImage;

  /** userName: */
  private String userName;

}
