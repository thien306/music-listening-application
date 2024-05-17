package com.codegym.controller;

import com.codegym.model.entity.Music;
import com.codegym.model.DTO.MusicForm;
import com.codegym.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
@RequestMapping("/musics")
@PropertySource("classpath:upload_file.properties")

public class MusicController {

    @Value("${upload}")
    private String upload;

    @Autowired
    private IMusicService musicService;

    @GetMapping("")
    public String index(Model model) {
        List<Music> musicList = musicService.findAll();
        model.addAttribute("musics", musicList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("music", new MusicForm());
        return "/create";
    }





    @PostMapping("/save")
    public String save(MusicForm musicForm) throws IOException {
//        tai file len
//        lay file
        MultipartFile file = musicForm.getSong();
//        lay ten
        String song = file.getOriginalFilename();
//        copy file vao trong thu muc
        FileCopyUtils.copy(file.getBytes(), new File(upload + song));
//        luu misic vao db
        Music music = new Music();
        music.setName(musicForm.getName());
        music.setArtist(musicForm.getArtist());
        music.setMusicGenre(musicForm.getMusicGenre());
        music.setLinkSong(song);
        musicService.save(music);
        return "redirect:/musics";
    }



    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("music", musicService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Music music) {
        musicService.save(music);
        return "redirect:/musics";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("music", musicService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Music music, RedirectAttributes redirect) {
        musicService.remove(music.getId());
        redirect.addAttribute("success", "Removed music successfully!");
        return "redirect:/musics";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("music", musicService.findById(id));
        return "/view";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, Model model){
        model.addAttribute("musics",musicService.findByName(name));
        return "/index";

    }
}
