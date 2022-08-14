package com.wkk.rpc.demo.client;

import com.wkk.rpc.demo.MusicServiceGrpc;
import com.wkk.rpc.demo.SingerId;
import com.wkk.rpc.demo.Song;
import com.wkk.rpc.demo.SongList;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/11
 */
@Slf4j
public class MusicClient {

    private final ManagedChannel channel;
    private final MusicServiceGrpc.MusicServiceBlockingStub blockingStub;
    private final MusicServiceGrpc.MusicServiceStub asyncStub;

    public MusicClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true)
                .build();
        blockingStub = MusicServiceGrpc.newBlockingStub(channel);
        asyncStub = MusicServiceGrpc.newStub(channel);
    }

    public void getSongList() {
        SingerId request = SingerId.newBuilder().setId(1).build();
        SongList songList = blockingStub.listSongs(request);
        for (Song song : songList.getSongsList()) {
            log.info(song.toString());
        }
    }

    public void getSongsUsingStream() {
        SingerId request = SingerId.newBuilder().setId(1).build();
        Iterator<Song> iterator = blockingStub.getSongs(request);
        while (iterator.hasNext()) {
            log.info(iterator.next().toString());
        }
    }

    public void getSongsUsingAsyncStub() throws InterruptedException {

        SingerId request = SingerId.newBuilder().setId(1).build();
        final CountDownLatch latch = new CountDownLatch(1); // using CountDownLatch

        StreamObserver<Song> responseObserver = new StreamObserver<Song>() {
            @Override
            public void onNext(Song value) {
                log.info("get song :" + value.toString());
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                log.info("failed with status : " + status );
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                log.info("finished!");
                latch.countDown();
            }
        };

        asyncStub.getSongs(request, responseObserver);

        latch.await();
    }

}
