// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Music.proto

package com.wkk.rpc.demo;

public interface SongListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:SongList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .Song songs = 1;</code>
   */
  java.util.List<com.wkk.rpc.demo.Song> 
      getSongsList();
  /**
   * <code>repeated .Song songs = 1;</code>
   */
  com.wkk.rpc.demo.Song getSongs(int index);
  /**
   * <code>repeated .Song songs = 1;</code>
   */
  int getSongsCount();
  /**
   * <code>repeated .Song songs = 1;</code>
   */
  java.util.List<? extends com.wkk.rpc.demo.SongOrBuilder> 
      getSongsOrBuilderList();
  /**
   * <code>repeated .Song songs = 1;</code>
   */
  com.wkk.rpc.demo.SongOrBuilder getSongsOrBuilder(
      int index);
}