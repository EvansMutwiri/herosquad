import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App {
    public static void main(String[] args) {
//heroku
        ProcessBuilder processBuilder = new ProcessBuilder();
        Integer port;
        if (processBuilder.environment().get("PORT") != null) {
            port= Integer.parseInt(processBuilder.environment().get("PORT"));
        }else {
            port =4567;
        }
        port(port);

        staticFileLocation("/public"); //app executable method

        post("/posts/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();

            String name = request.queryParams("name");
            String specialPower = request.queryParams("specialPower");
            String age = request.queryParams("age");
            String weakness = request.queryParams("weakness");
            Hero newHero = new Hero(name, age, specialPower, weakness);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> { //get all heros
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> posts = Hero.getAll();
            model.put("posts", posts);

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //Create
        get("/posts/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model,"squadform.hbs");
        },new HandlebarsTemplateEngine());

        post("/squad/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String cause = req.queryParams("cause");
            int maximum = Integer.parseInt(req.queryParams("maximum"));
            Squad squad = new Squad(name,cause,maximum);
            model.put("squad", squad);
            return new ModelAndView(model,"success-squad.hbs");
        },new HandlebarsTemplateEngine());

        //Read
        get("/posts/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params(":id"));
            Hero foundHero = Hero.findById(idOfHeroToFind);
            model.put("post", foundHero); //add it to model for template to display
            return new ModelAndView(model, "herodetail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad/list", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squad-details.hbs");
        }, new HandlebarsTemplateEngine());

        //Update get

        get("/posts/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/posts/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newContent = req.queryParams("content");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);

            editHero.update(newContent);
            return new ModelAndView(model, "edited.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int toDelete = Integer.parseInt(req.params("id"));
            Hero deleteHero = Hero.findById(toDelete);
            deleteHero.deleteHero();
            return new ModelAndView(model, "edited.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
