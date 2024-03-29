package com.wkk.rpc.demo;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.2.0)",
    comments = "Source: Music.proto")
public final class MusicServiceGrpc {

  private MusicServiceGrpc() {}

  public static final String SERVICE_NAME = "MusicService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.wkk.rpc.demo.SingerId,
      com.wkk.rpc.demo.SongList> METHOD_LIST_SONGS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "MusicService", "ListSongs"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.wkk.rpc.demo.SingerId.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.wkk.rpc.demo.SongList.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.wkk.rpc.demo.SingerId,
      com.wkk.rpc.demo.Song> METHOD_GET_SONGS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "MusicService", "GetSongs"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.wkk.rpc.demo.SingerId.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.wkk.rpc.demo.Song.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MusicServiceStub newStub(io.grpc.Channel channel) {
    return new MusicServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MusicServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MusicServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static MusicServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MusicServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MusicServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void listSongs(com.wkk.rpc.demo.SingerId request,
        io.grpc.stub.StreamObserver<com.wkk.rpc.demo.SongList> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LIST_SONGS, responseObserver);
    }

    /**
     */
    public void getSongs(com.wkk.rpc.demo.SingerId request,
        io.grpc.stub.StreamObserver<com.wkk.rpc.demo.Song> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_SONGS, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LIST_SONGS,
            asyncUnaryCall(
              new MethodHandlers<
                com.wkk.rpc.demo.SingerId,
                com.wkk.rpc.demo.SongList>(
                  this, METHODID_LIST_SONGS)))
          .addMethod(
            METHOD_GET_SONGS,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.wkk.rpc.demo.SingerId,
                com.wkk.rpc.demo.Song>(
                  this, METHODID_GET_SONGS)))
          .build();
    }
  }

  /**
   */
  public static final class MusicServiceStub extends io.grpc.stub.AbstractStub<MusicServiceStub> {
    private MusicServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MusicServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MusicServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MusicServiceStub(channel, callOptions);
    }

    /**
     */
    public void listSongs(com.wkk.rpc.demo.SingerId request,
        io.grpc.stub.StreamObserver<com.wkk.rpc.demo.SongList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LIST_SONGS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSongs(com.wkk.rpc.demo.SingerId request,
        io.grpc.stub.StreamObserver<com.wkk.rpc.demo.Song> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_SONGS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MusicServiceBlockingStub extends io.grpc.stub.AbstractStub<MusicServiceBlockingStub> {
    private MusicServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MusicServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MusicServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MusicServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.wkk.rpc.demo.SongList listSongs(com.wkk.rpc.demo.SingerId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LIST_SONGS, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.wkk.rpc.demo.Song> getSongs(
        com.wkk.rpc.demo.SingerId request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_SONGS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MusicServiceFutureStub extends io.grpc.stub.AbstractStub<MusicServiceFutureStub> {
    private MusicServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MusicServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MusicServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MusicServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.wkk.rpc.demo.SongList> listSongs(
        com.wkk.rpc.demo.SingerId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LIST_SONGS, getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_SONGS = 0;
  private static final int METHODID_GET_SONGS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MusicServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MusicServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_SONGS:
          serviceImpl.listSongs((com.wkk.rpc.demo.SingerId) request,
              (io.grpc.stub.StreamObserver<com.wkk.rpc.demo.SongList>) responseObserver);
          break;
        case METHODID_GET_SONGS:
          serviceImpl.getSongs((com.wkk.rpc.demo.SingerId) request,
              (io.grpc.stub.StreamObserver<com.wkk.rpc.demo.Song>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class MusicServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.wkk.rpc.demo.Music.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MusicServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MusicServiceDescriptorSupplier())
              .addMethod(METHOD_LIST_SONGS)
              .addMethod(METHOD_GET_SONGS)
              .build();
        }
      }
    }
    return result;
  }
}
