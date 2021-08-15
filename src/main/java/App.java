import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Hero;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public"); //app executable method

        post("/posts/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();

            String content = request.queryParams("content");
            String power = request.queryParams("power");
            String age = request.queryParams("age");
            Hero newAge = new Hero(age);
            Hero newPower = new Hero(power);
            Hero newHero = new Hero(content);
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

        //Read
        get("/posts/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params(":id"));
            Hero foundHero = Hero.findById(idOfHeroToFind);
            model.put("post", foundHero); //add it to model for template to display
            return new ModelAndView(model, "herodetail.hbs");
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
