package com.wkk.rpc.demo.service;

import com.wkk.rpc.demo.MusicServiceGrpc;
import com.wkk.rpc.demo.Singer;
import com.wkk.rpc.demo.SingerId;
import com.wkk.rpc.demo.Song;
import com.wkk.rpc.demo.SongList;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/6/11
 */
public class MusicServiceImpl extends MusicServiceGrpc.MusicServiceImplBase {

    @Override
    public void listSongs(SingerId request, StreamObserver<SongList> responseObserver) {
        SongList list = SongList.newBuilder().addAllSongs(genFakeSongs(request)).build();
        responseObserver.onNext(list);
        responseObserver.onCompleted();
    }

    @Override
    public void getSongs(SingerId request, StreamObserver<Song> responseObserver) {
        List<Song> songs = genFakeSongs(request);
        for (Song song: songs) {
            responseObserver.onNext(song);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                responseObserver.onError(e);
            }
        }
        responseObserver.onCompleted();
    }

    private List<Song> genFakeSongs(SingerId request) {
        List<Song> songList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Song.Builder builder = Song.newBuilder();
            Singer.Builder singerBuilder = Singer.newBuilder();
            singerBuilder.setId(i);
            singerBuilder.setName("singer name" + i);
            builder.setId(i);
            builder.setName("song name " + i);
            builder.setSinger(singerBuilder.build());
            songList.add(builder.build());
        }
        return songList;
    }
}
