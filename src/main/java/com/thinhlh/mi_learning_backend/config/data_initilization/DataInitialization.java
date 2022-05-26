package com.thinhlh.mi_learning_backend.config.data_initilization;

import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleRequest;
import com.thinhlh.mi_learning_backend.app.article.domain.entity.Article;
import com.thinhlh.mi_learning_backend.app.article.domain.service.ArticleService;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.auth.domain.service.AuthService;
import com.thinhlh.mi_learning_backend.app.category.controller.dto.CategoryRequest;
import com.thinhlh.mi_learning_backend.app.category.domain.service.CategoryService;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.role.data.repository.RoleRepository;
import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.app.role.domain.service.RoleService;
import com.thinhlh.mi_learning_backend.app.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@RequiredArgsConstructor
@Configuration
public class DataInitialization {

    private final AuthService authService;
    private final ArticleService articleService;
    private final RoleService roleService;
    private final UserService userService;
    private final CourseService courseService;

    private final CategoryService categoryService;

    @Bean
    @Order(1)
    CommandLineRunner initRoles() {
        return args -> {
            Arrays.stream(Role.RoleName.values()).forEach(roleName -> {
                roleService.createRole(new Role(UUID.randomUUID(), roleName.name()));
            });
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner initUsers() {
        return args -> {
            Arrays.stream(Role.RoleName.values()).toList().forEach(roleName -> {
                authService.registerUser(
                        RegisterRequest.builder()
                                .name(roleName.name())
                                .password(roleName.name())
                                .email(roleName.name() + "@gmail.com")
                                .occupation(roleName.name())
                                .birthday(LocalDate.now())
                                .role(roleName.name())
                                .build()
                );
            });
        };
    }

    @Bean
    @Order(3)
    CommandLineRunner initCategories() {
        return args -> {
            new ArrayList<String>() {{
                add("Flutter");
                add("Python");
                add("Web3");
                add("IOS");
                add("Django");
                add("Fast API");
                add("Machine Learning");
                add("Dev & Life");
            }}.forEach(title -> {
                categoryService.createCategory(
                        CategoryRequest
                                .builder()
                                .title(title)
                                .build());
            });
        };
    }

    @Bean
    @Order(4)
    CommandLineRunner initArticles() {
        return args -> {
            new ArrayList<ArticleRequest>() {{
                add(
                        ArticleRequest
                                .builder()
                                .author("nhancv")
                                .createdDate(LocalDate.of(2022, Month.APRIL, 19))
                                .thumbnail("https://miro.medium.com/max/1400/1*2fyw9zqntb47qCnXcuSLtw.png")
                                .title("How fast to take AWS Certificates?")
                                .url("https://nhancv.com/how-fast-to-take-aws-certificates/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("nhancv")
                                .createdDate(LocalDate.of(2022, Month.MARCH, 14))
                                .thumbnail("https://monetum.com/wp-content/uploads/2021/09/solana2-e1631861887338.png")
                                .title("Solidity Smart Contract Convention X")
                                .url("https://nhancv.com/solidity-smart-contract-convention-x/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("nhancv")
                                .createdDate(LocalDate.of(2022, Month.JANUARY, 8))
                                .thumbnail("https://docs.aws.amazon.com/whitepapers/latest/web-application-hosting-best-practices/images/image4.png")
                                .title("An AWS Cloud architecture for web hosting 3-Tiers")
                                .url("https://nhancv.com/an-aws-cloud-architecture-for-web-hosting%e2%80%8a-%e2%80%8a3-tiers/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Murali Krishna Ramanathan and Milind Chabbi")
                                .createdDate(LocalDate.of(2022, Month.APRIL, 28))
                                .thumbnail("https://1fykyq3mdn5r21tpna3wkdyi-wpengine.netdna-ssl.com/wp-content/uploads/2022/04/iStock-466268301-768x509.jpg")
                                .title("Dynamic Data Race Detection in Go Code")
                                .url("https://eng.uber.com/dynamic-data-race-detection-in-go-code/¬")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Xinli Shang")
                                .createdDate(LocalDate.of(2022, Month.MARCH, 10))
                                .thumbnail("https://1fykyq3mdn5r21tpna3wkdyi-wpengine.netdna-ssl.com/wp-content/uploads/2022/03/1-1-3-768x457.png")
                                .title("One Stone, Three Birds: Finer-Grained Encryption @ Apache Parquet™")
                                .url("https://eng.uber.com/one-stone-three-birds-finer-grained-encryption-apache-parquet/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Ramit Hora")
                                .createdDate(LocalDate.of(2022, Month.FEBRUARY, 10))
                                .thumbnail("https://1fykyq3mdn5r21tpna3wkdyi-wpengine.netdna-ssl.com/wp-content/uploads/2022/02/cover_figure-768x455.png")
                                .title("DeepETA: How Uber Predicts Arrival Times Using Deep Learning")
                                .url("https://eng.uber.com/deepeta-how-uber-predicts-arrival-times/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Alex Hutter")
                                .createdDate(LocalDate.of(2022, Month.APRIL, 13))
                                .thumbnail("https://miro.medium.com/max/1400/0*zWqzYt1KvSpkVSSL")
                                .title("How Netflix Content Engineering makes a federated graph searchable")
                                .url("https://netflixtechblog.com/how-netflix-content-engineering-makes-a-federated-graph-searchable-5c0c1c7d7eaf")
                                .build()
                );


                add(
                        ArticleRequest
                                .builder()
                                .author("mazdak hashemi")
                                .createdDate(LocalDate.of(2017, Month.JANUARY, 19))
                                .thumbnail("https://cdn.cms-twdigitalassets.com/content/dam/blog-twitter/engineering/en_us/infrastructure/2017/behind-twitter-scale/eng_infra_007.png.img.fullhd.medium.png")
                                .title("The Infrastructure Behind Twitter: Scale")
                                .url("https://blog.twitter.com/engineering/en_us/topics/infrastructure/2017/the-infrastructure-behind-twitter-scale")
                                .build()
                );


                add(
                        ArticleRequest
                                .builder()
                                .author("Maria Gorinova")
                                .createdDate(LocalDate.of(2022, Month.MARCH, 17))
                                .thumbnail("https://cdn.cms-twdigitalassets.com/content/dam/blog-twitter/engineering/en_us/insights/2022/missing-node-features/image2.png.img.fullhd.medium.png")
                                .title("Graph machine learning with missing node features")
                                .url("https://blog.twitter.com/engineering/en_us/topics/insights/2022/graph-machine-learning-with-missing-node-features")
                                .build()
                );


                add(
                        ArticleRequest
                                .builder()
                                .author("Sahib Pandori")
                                .createdDate(LocalDate.of(2022, Month.FEBRUARY, 14))
                                .thumbnail("https://cdn.cms-twdigitalassets.com/content/dam/blog-twitter/engineering/en_us/infrastructure/2022/manhattan-data-transfer/image1.png.img.fullhd.medium.png")
                                .title("Data transfer in Manhattan using RocksDB")
                                .url("https://blog.twitter.com/engineering/en_us/topics/insights/2022/graph-machine-learning-with-missing-node-features")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Justin Anderson")
                                .createdDate(LocalDate.of(2022, Month.FEBRUARY, 4))
                                .thumbnail("https://cdn.cms-twdigitalassets.com/content/dam/blog-twitter/engineering/en_us/open-source/2022/apache-thrift/image1.png.img.fullhd.medium.png")
                                .title("Introducing a new Swift package for Apache Thrift")
                                .url("https://blog.twitter.com/engineering/en_us/topics/open-source/2022/introducing-twitter-apache-thrift")
                                .build()
                );


                add(
                        ArticleRequest
                                .builder()
                                .author("Tim Sneath")
                                .createdDate(LocalDate.of(2022, Month.JANUARY, 8))
                                .thumbnail("https://miro.medium.com/max/1400/0*ZQ9Xa7CINFVMA95w")
                                .title("Introducing Flutter 3")
                                .url("https://medium.com/flutter/introducing-flutter-3-5eb69151622f")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Codemagic")
                                .createdDate(LocalDate.of(2022, Month.MARCH, 24))
                                .thumbnail("https://blog.codemagic.io/uploads/covers/codemagic-blog-header-Flutter-M1.png")
                                .title("Flutter builds are way faster with M1 machines")
                                .url("https://blog.codemagic.io/flutter-m1-vm-comparison/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Djangocentral")
                                .createdDate(LocalDate.of(2021, Month.JANUARY, 7))
                                .thumbnail("https://djangocentral.com/media/uploads/screely-1552995251107_JMYje3Q.png")
                                .title("Building A Blog Application With Django")
                                .url("https://djangocentral.com/building-a-blog-application-with-django/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Adam Johnson")
                                .createdDate(LocalDate.of(2021, Month.DECEMBER, 16))
                                .thumbnail("https://adamj.eu/tech/assets/2021-12-16-revolving-disc.jpg")
                                .title("Automatically Reload Your Browser in Development")
                                .url("https://adamj.eu/tech/2021/12/16/introducing-django-browser-reload/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Abdulazeez Abdulazeez Adeshina")
                                .createdDate(LocalDate.of(2020, Month.NOVEMBER, 28))
                                .thumbnail("https://raw.githubusercontent.com/roman-right/beanie/main/assets/logo/white_bg.svg")
                                .title("Building a CRUD App with FastAPI, MongoDB, and Beanie")
                                .url("https://djangocentral.com/building-a-blog-application-with-django/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Takuya Matsuyama")
                                .createdDate(LocalDate.of(2021, Month.DECEMBER, 21))
                                .thumbnail("https://miro.medium.com/max/1400/0*6jI576ctpBUF7eMH.png")
                                .title("What I accomplished in 2021 & my challenge for 2022")
                                .url("https://blog.inkdrop.app/what-i-accomplished-in-2021-my-challenge-for-2022-86bcf1d29ea1")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Takuya Matsuyama")
                                .createdDate(LocalDate.of(2021, Month.JANUARY, 28))
                                .thumbnail("https://miro.medium.com/max/1400/1*pHDobfHmUUsYnkskWpCAvg.png")
                                .title("I’ll take a 3-week paternity leave")
                                .url("https://blog.inkdrop.app/ill-take-a-3-week-paternity-leave-4ff66b36c8cc")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Dev as Life")
                                .createdDate(LocalDate.of(2021, Month.JANUARY, 7))
                                .thumbnail("https://miro.medium.com/max/1400/1*2Gq4l4XtHQaa3gQke9jhag.jpeg")
                                .title("Running a React Native app on Android Emulator in M1 Mac")
                                .url("https://blog.inkdrop.app/running-a-react-native-app-on-android-emulator-in-m1-mac-76a16348d1e4")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("solanablog")
                                .createdDate(LocalDate.of(2021, Month.NOVEMBER, 25))
                                .thumbnail("https://djangocentral.com/media/uploads/screely-1552995251107_JMYje3Q.png")
                                .title("How Environmentally Green is Solana Blockchain?")
                                .url("https://solana.blog/how-environmentally-green-is-solana-blockchain/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("solanablog")
                                .createdDate(LocalDate.of(2021, Month.AUGUST, 8))
                                .thumbnail("https://solana.blog/wp-content/uploads/sites/8/2021/08/solana-breakpoint.png")
                                .title("Solana Breakpoint Conference Nov 2021")
                                .url("https://solana.blog/solana-breakpoint-conference-nov-2021/")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Flutter awesome")
                                .createdDate(LocalDate.of(2022, Month.MARCH, 25))
                                .thumbnail("https://flutterawesome.com/content/images/2022/05/SQLiteWrapper.png")
                                .title("A simple way to easily use the SQLite library from Dart and Flutter")
                                .url("https://feedly.com/i/entry/QDe32UKlTyAUFiUbv2G2FWNoxgNbC4IVQjQY6b6cOWo=_180fa5d3272:3d46e55:6459d2ee")
                                .build()
                );

                add(
                        ArticleRequest
                                .builder()
                                .author("Very good ventures")
                                .createdDate(LocalDate.of(2022, Month.JANUARY, 12))
                                .thumbnail("https://raw.githubusercontent.com/VeryGoodOpenSource/dart_frog/main/assets/dart_frog_logo_black.png#gh-light-mode-only")
                                .title("A fast, minimalistic backend framework for Dart")
                                .url("https://flutterawesome.com/a-fast-minimalistic-backend-framework-for-dart/")
                                .build()
                );

            }}.forEach(articleService::createArticle);
        };
    }

    @Bean
    @Order(5)
    CommandLineRunner initCourses() {
        return args -> {
            new ArrayList<Course>() {{
                var categories = categoryService.getAllCategories();

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Flutter TDD Clean Architecture Course")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2019/08/thumbnail-3.png?resize=300%2C169&ssl=1")
                        .description("Keeping your code clean and tested are the two most important development practices. In Flutter, this is even more true than with other frameworks. On one hand, it's nice to hack a quick app together, on the other hand, larger projects start falling apart when you mix the business logic everywhere. Even state management patterns like BLoC are not sufficient in themselves to allow for easily extendable codebase.\nwhile the essence of clean architecture remains the same for every framework, the devil lies in the details. Principles like SOLID and YAGNI sound nice, you may even understand what they mean, but it won't do you any good if you don't know how to start writing clean code.")
                        .length(24 * 60 * 60)
                        .price(20.99)
                        .category(categories.get(0))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Flutter Firebase & DDD Course – Domain-Driven Design Principles")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2020/03/thumbnail_ddd_1.png?fit=1920%2C1080&")
                        .description("Flutter apps need structure that is easy to orient yourself in, testable and maintainable. It also wouldn't hurt if the way you architect your Flutter apps allows for adding new features without a headache. Especially with a client-centric service such as Firebase Firestore, it's extremely important to keep your code clean. Let's do it by following the principles of Domain-Driven Design.")
                        .length(12 * 60 * 60)
                        .price(18.99)
                        .category(categories.get(0))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Riverpod 2.0 – Complete Guide (Flutter Tutorial)")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2022/04/riverpod2_thumbnail.jpeg?fit=1921%2C1081&")
                        .description("If you’ve been at least a bit active when it comes to Flutter packages in the last year or so, you’ve surely heard about Riverpod, a reactive caching and data-binding, or as some would say, state management package that is sort of an upgrade of the beloved Provider. I actually covered it with a tutorial quite some time ago when its API was still unstable.Riverpod has come a long way since then - it’s much more mature, helpful, and versatile. All these changes naturally mean that it’s time for a new tutorial to prepare you to fully utilize the power of Riverpod 2.0 and, most likely, also its upcoming versions.")
                        .category(categories.get(0))
                        .length(1 * 60 * 60)
                        .price(2.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Environments (Flavors) in Flutter with Codemagic CI/CD")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2020/02/thumbnail_environments.png?fit=300%2C169&")
                        .description("You are tweaking an app which is already in production. You are implementing code that allows a user to delete his data. All of a sudden, you realize that you made a huge mistake! By providing a wrong ID, you accidentally deleted data of an actual user! \nHorror stories like this one can truly become a reality if you don't have separate production and development environments. Thankfully, it's very easy to set all of this up with Codemagic which is a CI/CD service dedicated specifically for Flutter apps.")
                        .category(categories.get(0))
                        .length(1 * 60 * 60)
                        .price(2.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Flutter Testing Guide for Beginners")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2022/03/thumbnail_flutter_testing_2.png?fit=300%2C169&")
                        .description("How can you make sure that an app does exactly what it should do without any weird unexpected surprises? Well, you test it, of course. You could test everything manually by launching the app, using it, and trying your best to make the app blow up with errors. Or you can write a bunch of automated tests, which is arguably a more time-efficient and thorough way to test your apps. Let’s take a look at unit, widget, and integration tests in the video tutorial above.")
                        .length(4 * 60 * 60)
                        .category(categories.get(0))
                        .price(4.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Flutter Bloc Library Tutorial - Reactive State Management")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2019/10/thumbnail-5.png?fit=300%2C169&")
                        .description("State management is needed by every app. No matter the size of your project, you need to store and do something with all the data present in your app. If you're building something small, you might be able to pull it off with StatefulWidgets. As the difficulty of the project starts to grow, you have to start looking for more maintainable solutions...The flutter_bloc package is a reactive and predictable way to manage your app's state. This package takes everything that's awesome about the BLoC (business logic component) pattern and puts it into a simple-to-use library with amazing tooling. After many months of development, the Bloc package has arrived at its first stable version - 1.0.0.")
                        .length(10 * 60 * 60)
                        .category(categories.get(0))
                        .price(15.99)
                        .build());


                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("100 Days of Code: The Complete Python Pro Bootcamp for 2022")
                        .background("https://img-b.udemycdn.com/course/480x270/2776760_f176_10.jpg")
                        .description("Master Python by building 100 projects in 100 days. Learn data science, automation, build websites, games and apps!")
                        .length(60 * 60 * 60)
                        .category(categories.get(1))
                        .price(72.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Learn Python Programming Masterclass")
                        .background("https://img-b.udemycdn.com/course/240x135/629302_8a2d_2.jpg")
                        .description("This course is aimed at complete beginners who have never programmed before, as well as existing programmers who want to increase their career options by learning Python. The fact is, Python is one of the most popular programming languages in the world – Huge companies like Google use it in mission critical applications like Google Search. And Python is the number one language choice for machine learning, data science and artificial intelligence. To get those high paying jobs you need an expert knowledge of Python, and that’s what you will get from this course.")
                        .length(70 * 60 * 60)
                        .category(categories.get(1))
                        .price(99.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("100 Days of Code: The Complete Python Pro Bootcamp for 2022")
                        .background("https://img-b.udemycdn.com/course/480x270/2776760_f176_10.jpg")
                        .description("Master Python by building 100 projects in 100 days. Learn data science, automation, build websites, games and apps!")
                        .length(14 * 60 * 60)
                        .category(categories.get(1))
                        .price(72.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Introduction to Token Engineering for Web3 Platforms")
                        .background("https://img-b.udemycdn.com/course/240x135/4698152_7a45_3.jpg")
                        .description("Tokens are often touted as investment opportunities but the primary role of tokens on web3 platforms is to capture the value generated by the platforms and distribute value and control among the network participants to incentivize them so that they behave in a way that contributes to the smooth functioning and growth of the ecosystem itself.")
                        .length(1 * 60 * 60)
                        .category(categories.get(2))
                        .price(10.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Ethereum Blockchain Developer Bootcamp With Solidity (2022)")
                        .background("https://img-b.udemycdn.com/course/240x135/1172526_e914_8.jpg")
                        .description("Welcome to the Ethereum Blockchain Developer Bootcamp With Solidity. The only course you'll need to become an Ethereum blockchain developer. With over 1,900 5 stars reviews, this course is one of the highest-rated Ethereum blockchain development courses online.")
                        .length(12 * 60 * 60)
                        .category(categories.get(2))
                        .price(55.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Complete DApp - Solidity & React - Blockchain Development")
                        .background("https://img-b.udemycdn.com/course/480x270/4211990_0ea9_2.jpg")
                        .description("Welcome to the Complete iOS App Development Bootcamp. With over 39,000 5 star ratings and a 4.8 average my iOS course is the HIGHEST RATED iOS Course in the history of Udemy! At 55+ hours, this iOS 13 course is the most comprehensive iOS development course online! This Swift 5.1 course is based on our in-person app development bootcamp in London, where we've perfected the curriculum over 4 years of in-person teaching. Our complete app development bootcamp teaches you how to code using Swift 5.1 and build beautiful iOS 13 apps for iPhone and iPad. Even if you have ZERO programming experience. I'll take you step-by-step through engaging and fun video tutorials and teach you everything you need to know to succeed as an iOS app developer. The course includes 55+ hours of HD video tutorials and builds your programming knowledge while making real world apps. e.g. Pokemon Go, Whatsapp, QuizUp and Yahoo Weather.")
                        .length(16 * 60 * 60)
                        .category(categories.get(2))
                        .price(9.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("iOS & Swift - The Complete iOS App Development Bootcamp")
                        .background("https://img-b.udemycdn.com/course/240x135/1778502_f4b9_12.jpg")
                        .description("Welcome to the Complete iOS App Development Bootcamp. With over 39,000 5 star ratings and a 4.8 average my iOS course is the HIGHEST RATED iOS Course in the history of Udemy! At 55+ hours, this iOS 13 course is the most comprehensive iOS development course online! This Swift 5.1 course is based on our in-person app development bootcamp in London, where we've perfected the curriculum over 4 years of in-person teaching. Our complete app development bootcamp teaches you how to code using Swift 5.1 and build beautiful iOS 13 apps for iPhone and iPad. Even if you have ZERO programming experience. I'll take you step-by-step through engaging and fun video tutorials and teach you everything you need to know to succeed as an iOS app developer. The course includes 55+ hours of HD video tutorials and builds your programming knowledge while making real world apps. e.g. Pokemon Go, Whatsapp, QuizUp and Yahoo Weather.")
                        .length(55 * 60 * 60)
                        .category(categories.get(3))
                        .price(55.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("iOS 11 & Swift 4 - The Complete iOS App Development Bootcamp")
                        .background("https://img-b.udemycdn.com/course/240x135/1289478_5831_10.jpg")
                        .description("Welcome to the Complete iOS App Development Bootcamp. With over 39,000 5 star ratings and a 4.8 average my iOS course is the HIGHEST RATED iOS Course in the history of Udemy! At 55+ hours, this iOS 13 course is the most comprehensive iOS development course online! This Swift 5.1 course is based on our in-person app development bootcamp in London, where we've perfected the curriculum over 4 years of in-person teaching. Our complete app development bootcamp teaches you how to code using Swift 5.1 and build beautiful iOS 13 apps for iPhone and iPad. Even if you have ZERO programming experience. I'll take you step-by-step through engaging and fun video tutorials and teach you everything you need to know to succeed as an iOS app developer. The course includes 55+ hours of HD video tutorials and builds your programming knowledge while making real world apps. e.g. Pokemon Go, Whatsapp, QuizUp and Yahoo Weather.")
                        .length(55 * 60 * 60)
                        .category(categories.get(3))
                        .price(55.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("iOS & Swift - The Complete iOS App Development Bootcamp")
                        .background("https://img-b.udemycdn.com/course/240x135/1778502_f4b9_12.jpg")
                        .description("Welcome to the Complete iOS App Development Bootcamp. With over 17,000 5 star ratings, this is the HIGHEST RATED iOS Course of all time! This Swift 4 course is based on our in-person app development bootcamp in London. We've perfected the curriculum over 3 years of in-person teaching. Our complete app development bootcamp teaches you how to code using Swift 4 and build beautiful iOS 11 apps for iPhone and iPad. Even if you have ZERO programming experience.")
                        .length(49 * 60 * 60)
                        .category(categories.get(3))
                        .price(84.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("SwiftUI Masterclass 2022 - iOS 15 App Development & Swift 5")
                        .background("https://img-b.udemycdn.com/course/240x135/1693472_e1d5_13.jpg")
                        .description("Visually learn SwiftUI 3 and build top-notch iOS 15, iPadOS mobile apps, Apple Watch apps, and even macOS desktop applications. This complete iOS application development course is designed to teach you how to become an advanced multiplatform app developer using Apple's native user interface framework: SwiftUI. This class takes learning programming concepts through a project-based approach. By taking this class, you will improve your app design and development skills while creating many hands-on applications.")
                        .length(30 * 60 * 60)
                        .category(categories.get(3))
                        .price(19.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Python and Django Full Stack Web Developer Bootcamp")
                        .background("https://img-b.udemycdn.com/course/240x135/1778502_f4b9_12.jpg")
                        .description("Welcome to the Python and Django Full Stack Web Developer Bootcamp! In this course we cover everything you need to know to build a website using Python, Django, and many more web technologies! Whether you want to change career paths, expand your current skill set, start your own entrepreneurial business, become a consultant, or just want to learn, this is the course for you!")
                        .length(25 * 60 * 60)
                        .category(categories.get(4))
                        .price(20.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Django 4 and Python Full-Stack Developer Masterclass")
                        .background("https://img-b.udemycdn.com/course/240x135/822444_a6db.jpg")
                        .description("Welcome to the best online course for learning how to create websites using Python and Django! In this course we take you from zero to hero in web development with Django! Whether you want to change career paths, expand your current skill set, start your own entrepreneurial business, become a consultant, or just want to learn, this is the course for you!")
                        .length(19 * 60 * 60)
                        .category(categories.get(4))
                        .price(20.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Python Django - The Practical Guide")
                        .background("https://img-b.udemycdn.com/course/240x135/4015616_32a9_2.jpg")
                        .description("And to make that easier, you would typically use a framework like Django - simply because that allows you to focus on your core business logic and you don't need to re-invent the wheel and implement all the nitty-gritty technical details from scratch. And this course is about Django - the most popular Python web development framework out there!")
                        .length(23 * 60 * 60)
                        .category(categories.get(4))
                        .price(20.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("The FastAPI SuperGuide: Create 3 Real-World FastAPI Apps")
                        .background("https://img-b.udemycdn.com/course/240x135/4015616_32a9_2.jpg")
                        .description("Learn FastAPI, Python, REST APIs, Bootstrap, SQLite, Jinja, and web security; all while creating 3 full-stack web apps!")
                        .length(27 * 60 * 60)
                        .category(categories.get(5))
                        .price(20.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("The Complete FastAPI Course With OAuth & JWT Authentication")
                        .background("https://img-b.udemycdn.com/course/240x135/4265666_cd12_10.jpg")
                        .description("FastAPI is one of the most modern, fast and efficient framework for building APIs. If you want to learn how to built high performance APIs then FastAPI should be your go to framework. In this course we will learn FastAPI right from scratch and by then end of the course you will be able to build a complete API which supports authentication, JWT tokens, relational models and protected API routes.")
                        .length(15 * 60 * 60)
                        .category(categories.get(5))
                        .price(30.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("FastAPI - The Complete Course 2022 (Beginner + Advanced)")
                        .background("https://img-b.udemycdn.com/course/240x135/4355412_2065_5.jpg")
                        .description("FastAPI and Python are two of the hottest technologies in the market for building high performing APIs. By the end of this course, you will have built production ready RESTful APIs, a production ready Full Stack application, setup production ready databases, and deployed your FastAPI application so the world can use YOUR app.")
                        .length(34 * 60 * 60)
                        .category(categories.get(5))
                        .price(52.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("The Data Science Course 2022: Complete Data Science Bootcamp")
                        .background("https://img-c.udemycdn.com/course/240x135/2769460_e60c.jpg")
                        .description("Complete Data Science Training: Mathematics, Statistics, Python, Advanced Statistics in Python, Machine & Deep Learning")
                        .length(9 * 60 * 60)
                        .category(categories.get(6))
                        .price(52.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Complete Machine Learning & Data Science Bootcamp 2022")
                        .background("https://img-c.udemycdn.com/course/240x135/950390_270f_3.jpg")
                        .description("Learn Data Science, Data Analysis, Machine Learning (Artificial Intelligence) and Python with Tensorflow, Pandas & more!")
                        .length(16 * 60 * 60)
                        .category(categories.get(6))
                        .price(52.99)
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Bayesian Machine Learning in Python: A/B Testing")
                        .background("https://img-c.udemycdn.com/course/480x270/3693164_f87d_3.jpg")
                        .description("This course is all about A/B testing. A/B testing is used everywhere. Marketing, retail, newsfeeds, online advertising, and more. A/B testing is all about comparing things. If you’re a data scientist, and you want to tell the rest of the company, “logo A is better than logo B”, well you can’t just say that without proving it using numbers and statistics. Traditional A/B testing has been around for a long time, and it’s full of approximations and confusing definitions. In this course, while we will do traditional A/B testing in order to appreciate its complexity, what we will eventually get to is the Bayesian machine learning way of doing things.")
                        .length(10 * 60 * 60)
                        .category(categories.get(6))
                        .price(7.99)
                        .build());
            }}.forEach(courseService::createCourse);
        };
    }
}
