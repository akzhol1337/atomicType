package com.example.atomictype.Presentation;


import com.example.atomictype.Business.Entity.League;
import com.example.atomictype.Business.Entity.User;
import com.example.atomictype.Business.Service.LeagueService;
import com.example.atomictype.Business.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final LeagueService leagueService;

    @PostMapping("/user/save")
    public ResponseEntity saveUser(@RequestBody User user){
        if (userService.hasUser(user.getUsername())){
            return ResponseEntity.badRequest().body("User with this username already exist");
        }
        if (userService.hasEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("User with this email already exist");
        }
        user.setAverage_wpm_last10(0);
        user.setAverage_wpm_full(0);
        user.setBest_wpm(0);
        user.setExperience_rank("Newbie");
        user.setAccount_type("User");

        user.setNumber_of_races(0);
        user.setExperience_points(0);
        user.setCar_photo("car_photo_link");

        userService.saveUser(user);
        return new ResponseEntity("User has been created", HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user/{username}")
    public String getUserpage(Model model, @PathVariable String username, Principal principal){
        User user = userService.getUser(username);

        int accountType = user.getRoles().size();

        // statistics
        Integer numberOfRaces = user.getNumber_of_races();
        Integer bestRace = user.getBest_wpm();
        Integer averageWpmFull = user.getAverage_wpm_full();
        Integer averageWpmLast10 = user.getAverage_wpm_last10();
        String carPhoto = user.getCar_photo();
        String country = user.getCountry();
        String speedRank = user.getSpeed_rank();
        String profilePhoto = user.getProfile_photo();
        String experienceRank = user.getExperience_rank();

        List<com.example.atomictype.Business.Entity.Internal.User> followers = new ArrayList<>();
        List<com.example.atomictype.Business.Entity.Internal.User> followings = new ArrayList<>();
        List<String> leagues = new ArrayList<>();

        for(User follower : user.getFollowers()){
            followers.add(new com.example.atomictype.Business.Entity.Internal.User(follower.getUsername(), follower.getProfile_photo()));
        }

        for(User following : user.getFollowings()){
            followings.add(new com.example.atomictype.Business.Entity.Internal.User(following.getUsername(), following.getProfile_photo()));
        }

        for(League league : user.getLeagues()){
            leagues.add(league.getName());
        }
        boolean myAccount = principal != null && Objects.equals(principal.getName(), username);

        // sample data
        /*
        numberOfRaces = 5534;
        bestRace = 137;
        averageWpmFull = 86;
        averageWpmLast10 = 110;
        carPhoto = "car_photo";
        profilePhoto = "profile_photo";
        country = "UK";
        speedRank = "Global Elite";
        experienceRank = "Legendary";
        */

        model.addAttribute("accountType", accountType);
        model.addAttribute("numberOfRaces", numberOfRaces);
        model.addAttribute("bestRace", bestRace);
        model.addAttribute("averageWpmFull", averageWpmFull);
        model.addAttribute("averageWpmLast10", averageWpmLast10);
        model.addAttribute("carPhoto", carPhoto);
        model.addAttribute("profilePhoto", profilePhoto);
        model.addAttribute("country", country);
        model.addAttribute("speedRank", speedRank);
        model.addAttribute("experienceRank", experienceRank);
        model.addAttribute("myAccount", myAccount);
        model.addAttribute("followers", followers);
        model.addAttribute("followings", followings);
        model.addAttribute("leagues",  leagues);
        return "user";
    }


    /*@GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("Amiracle".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AUser user = userService.getUserByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_toke", access_token);
                tokens.put("refresh_toke", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is messing");
        }
    }*/


    @GetMapping("/league/{leagueName}")
    public String getLeague(@PathVariable String leagueName, Model model, Principal principal){
        League league = leagueService.findByName(leagueName);
        String loggedUsername = principal.getName();

        int accountType = 0;

        if(league.isUser(loggedUsername)){
            accountType = 1;
        } else if(league.isAdmin(loggedUsername)){
            accountType = 2;
        }

        model.addAttribute("leagueName", leagueName);
        model.addAttribute("accountType", accountType);
        return "league";
    }

    @GetMapping("/createLeague")
    public String createLeague(){
        return "createleague";
    }


}
