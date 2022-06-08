package com.wkk.rpc.core.client;

import com.wkk.rpc.core.rpc_protocol.RpcRequest;
import com.wkk.rpc.core.rpc_protocol.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/7
 */
@Slf4j
public class RpcClientTransfer {

    public RpcResponse sendRequest(RpcRequest request) {
        try(Socket socket = new Socket("localhost", 9000)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            RpcResponse readObject = (RpcResponse) objectInputStream.readObject();

            return readObject;

        } catch (IOException | ClassNotFoundException e) {
            log.error("client happened error", e);
            return null;
        }
    }
}
