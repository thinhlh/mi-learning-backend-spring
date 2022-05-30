package com.thinhlh.mi_learning_backend.config.data_initilization;

import com.thinhlh.mi_learning_backend.app.article.controller.dto.ArticleRequest;
import com.thinhlh.mi_learning_backend.app.article.domain.service.ArticleService;
import com.thinhlh.mi_learning_backend.app.auth.controller.dto.RegisterRequest;
import com.thinhlh.mi_learning_backend.app.auth.domain.service.AuthService;
import com.thinhlh.mi_learning_backend.app.category.controller.dto.CategoryRequest;
import com.thinhlh.mi_learning_backend.app.category.domain.service.CategoryService;
import com.thinhlh.mi_learning_backend.app.course.domain.entity.Course;
import com.thinhlh.mi_learning_backend.app.course.domain.service.CourseService;
import com.thinhlh.mi_learning_backend.app.lesson.controller.dto.LessonRequest;
import com.thinhlh.mi_learning_backend.app.lesson.domain.service.LessonService;
import com.thinhlh.mi_learning_backend.app.rating.controller.dto.RatingRequest;
import com.thinhlh.mi_learning_backend.app.rating.data.repository.RatingRepository;
import com.thinhlh.mi_learning_backend.app.rating.domain.service.RatingService;
import com.thinhlh.mi_learning_backend.app.role.domain.entity.Role;
import com.thinhlh.mi_learning_backend.app.role.domain.service.RoleService;
import com.thinhlh.mi_learning_backend.app.schedule.controller.dto.ScheduleRequest;
import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.ScheduleColor;
import com.thinhlh.mi_learning_backend.app.schedule.domain.entity.ScheduleStatus;
import com.thinhlh.mi_learning_backend.app.schedule.domain.service.ScheduleService;
import com.thinhlh.mi_learning_backend.app.section.controller.dto.SectionRequest;
import com.thinhlh.mi_learning_backend.app.section.domain.entity.Section;
import com.thinhlh.mi_learning_backend.app.section.domain.service.SectionService;
import com.thinhlh.mi_learning_backend.app.student_course.domain.service.StudentCourseService;
import com.thinhlh.mi_learning_backend.app.teacher.data.repository.TeacherRepository;
import com.thinhlh.mi_learning_backend.app.user.domain.service.UserService;
import com.thinhlh.mi_learning_backend.helper.ListHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

@RequiredArgsConstructor
@Configuration
public class DataInitialization {

    private final RatingRepository ratingRepository;
    private final TeacherRepository teacherRepository;

    private final AuthService authService;
    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final CourseService courseService;
    private final LessonService lessonService;
    private final StudentCourseService studentCourseService;
    private final RoleService roleService;
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final SectionService sectionService;
    private final RatingService ratingService;


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
    CommandLineRunner initTeachers() {
        return args -> {
            authService.registerUser(
                    RegisterRequest.builder()
                            .name("Nhan CV")
                            .password("nhancv")
                            .email("nhancv@gmail.com")
                            .occupation(Role.RoleName.teacher.name())
                            .birthday(LocalDate.now())
                            .role(Role.RoleName.teacher.name())
                            .avatar("https://source.unsplash.com/random/?avatar")
                            .build()
            );

            authService.registerUser(
                    RegisterRequest.builder()
                            .name("Reso Coder")
                            .password("resocoder")
                            .email("resocoder@gmail.com")
                            .occupation(Role.RoleName.teacher.name())
                            .birthday(LocalDate.now())
                            .role(Role.RoleName.teacher.name())
                            .avatar("https://source.unsplash.com/random/?avatar")
                            .build()
            );

            authService.registerUser(
                    RegisterRequest.builder()
                            .name("Angela Yu")
                            .password("angelayu")
                            .email("angelayu@gmail.com")
                            .occupation(Role.RoleName.teacher.name())
                            .birthday(LocalDate.now())
                            .role(Role.RoleName.teacher.name())
                            .avatar("https://source.unsplash.com/random/?avatar")
                            .build()
            );
        };
    }

    @Bean
    @Order(4)
    CommandLineRunner initStudents() {
        return args -> {
            authService.registerUser(
                    RegisterRequest.builder()
                            .name("Hoang Thinh")
                            .password("hoangthinh")
                            .email("hoangthinh@gmail.com")
                            .occupation(Role.RoleName.student.name())
                            .birthday(LocalDate.now())
                            .role(Role.RoleName.student.name())
                            .avatar("https://source.unsplash.com/random/?avatar")
                            .build()
            );
        };
    }

    @Bean
    @Order(5)
    CommandLineRunner initCategories() {
        return args -> {
            new LinkedHashMap<String, String>() {{
//                put("Flutter", "https://storage.googleapis.com/mi-learning.appspot.com/categories/flutter-course.jpeg");
//                put("Python", "https://storage.googleapis.com/mi-learning.appspot.com/categories/python.png");
//                put("Web3", "https://storage.googleapis.com/mi-learning.appspot.com/categories/Solana.jpeg");
//                put("IOS", "https://storage.googleapis.com/mi-learning.appspot.com/categories/swift.webp");
//                put("Django", "https://storage.googleapis.com/mi-learning.appspot.com/categories/python-django-logo.webp");
//                put("Fast API", "https://storage.googleapis.com/mi-learning.appspot.com/categories/fastapi.png");
//                put("Machine Learning", "https://storage.googleapis.com/mi-learning.appspot.com/categories/machine_learning_746x419.jpeg");
//                put("Architecture", "https://storage.googleapis.com/mi-learning.appspot.com/categories/amazon-academy-740x480.jpeg");
//                https://docs.flutter.dev/assets/images/flutter-logo-sharing.png

                put("Flutter", "https://docs.flutter.dev/assets/images/flutter-logo-sharing.png");
                put("Python", "https://vietnix.vn/wp-content/uploads/2021/07/python-la-gi.webp");
                put("Web3", "https://assets.entrepreneur.com/content/3x2/2000/1652275872-new34.jpg");
                put("IOS", "https://developer.apple.com/swift/images/swift-og.png");
                put("Django", "https://www.tma.vn/Media/Default/BaiDang/6-2.jpg");
                put("Fast API", "https://fastapi.tiangolo.com/img/logo-margin/logo-teal.png");
                put("Machine Learning", "https://funix.edu.vn/wp-content/uploads/2021/08/machine-learning.jpg");
                put("Architecture", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Amazon_Web_Services_Logo.svg/800px-Amazon_Web_Services_Logo.svg.png");
            }}.forEach((title, background) -> {
                categoryService.createCategory(
                        CategoryRequest
                                .builder()
                                .title(title)
                                .background(background)
                                .build());
            });
        };
    }

    @Bean
    @Order(6)
    CommandLineRunner initArticles() {
        return args -> {
            var categories = categoryService.getAllCategories();
            new ArrayList<ArticleRequest>() {{
                add(
                        ArticleRequest
                                .builder()
                                .author("nhancv")
                                .createdDate(LocalDate.of(2022, Month.APRIL, 19))
                                .thumbnail("https://miro.medium.com/max/1400/1*2fyw9zqntb47qCnXcuSLtw.png")
                                .category(categories.get(7).getTitle())
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
                                .category(categories.get(2).getTitle())
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
                                .category(categories.get(7).getTitle())
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
                                .category(categories.get(7).getTitle())
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
                                .category(categories.get(7).getTitle())
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
                                .category(categories.get(6).getTitle())
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
                                .category(categories.get(7).getTitle())
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
                                .category(categories.get(7).getTitle())
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
                                .category(categories.get(6).getTitle())
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
                                .category(categories.get(7).getTitle())
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
                                .category(categories.get(4).getTitle())
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
                                .category(categories.get(0).getTitle())
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
                                .category(categories.get(0).getTitle())
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
                                .category(categories.get(1).getTitle())
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
                                .category(categories.get(5).getTitle())
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
                                .category(categories.get(5).getTitle())
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
                                .category(categories.get(2).getTitle())
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
                                .category(categories.get(2).getTitle())
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
                                .category(categories.get(3).getTitle())
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
                                .category(categories.get(2).getTitle())
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
                                .category(categories.get(2).getTitle())
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
                                .category(categories.get(0).getTitle())
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
                                .category(categories.get(0).getTitle())
                                .url("https://flutterawesome.com/a-fast-minimalistic-backend-framework-for-dart/")
                                .build()
                );

            }}.forEach(articleService::createArticle);
        };
    }

    @Bean
    @Order(7)
    CommandLineRunner initCourses() {
        return args -> {
            var categories = categoryService.getAllCategories();
            var teachers = ListHelper.toList(teacherRepository.findAll());
            var courses = new ArrayList<Course>() {{

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Flutter TDD Clean Architecture Course")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2019/08/thumbnail-3.png?resize=300%2C169&ssl=1")
                        .description("Keeping your code clean and tested are the two most important development practices. In Flutter, this is even more true than with other frameworks. On one hand, it's nice to hack a quick app together, on the other hand, larger projects start falling apart when you mix the business logic everywhere. Even state management patterns like BLoC are not sufficient in themselves to allow for easily extendable codebase.\nwhile the essence of clean architecture remains the same for every framework, the devil lies in the details. Principles like SOLID and YAGNI sound nice, you may even understand what they mean, but it won't do you any good if you don't know how to start writing clean code.")
                        .length(24 * 60 * 60)
                        .price(20.99)
                        .category(categories.get(0))
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Flutter Firebase & DDD Course – Domain-Driven Design Principles")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2020/03/thumbnail_ddd_1.png?fit=1920%2C1080&")
                        .description("Flutter apps need structure that is easy to orient yourself in, testable and maintainable. It also wouldn't hurt if the way you architect your Flutter apps allows for adding new features without a headache. Especially with a client-centric service such as Firebase Firestore, it's extremely important to keep your code clean. Let's do it by following the principles of Domain-Driven Design.")
                        .length(12 * 60 * 60)
                        .price(18.99)
                        .category(categories.get(0))
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Riverpod 2.0 – Complete Guide (Flutter Tutorial)")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2022/04/riverpod2_thumbnail.jpeg?fit=1921%2C1081&")
                        .description("If you’ve been at least a bit active when it comes to Flutter packages in the last year or so, you’ve surely heard about Riverpod, a reactive caching and data-binding, or as some would say, state management package that is sort of an upgrade of the beloved Provider. I actually covered it with a tutorial quite some time ago when its API was still unstable.Riverpod has come a long way since then - it’s much more mature, helpful, and versatile. All these changes naturally mean that it’s time for a new tutorial to prepare you to fully utilize the power of Riverpod 2.0 and, most likely, also its upcoming versions.")
                        .category(categories.get(0))
                        .length(1 * 60 * 60)
                        .price(2.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Environments (Flavors) in Flutter with Codemagic CI/CD")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2020/02/thumbnail_environments.png?fit=300%2C169&")
                        .description("You are tweaking an app which is already in production. You are implementing code that allows a user to delete his data. All of a sudden, you realize that you made a huge mistake! By providing a wrong ID, you accidentally deleted data of an actual user! \nHorror stories like this one can truly become a reality if you don't have separate production and development environments. Thankfully, it's very easy to set all of this up with Codemagic which is a CI/CD service dedicated specifically for Flutter apps.")
                        .category(categories.get(0))
                        .length(1 * 60 * 60)
                        .price(2.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Flutter Testing Guide for Beginners")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2022/03/thumbnail_flutter_testing_2.png?fit=300%2C169&")
                        .description("How can you make sure that an app does exactly what it should do without any weird unexpected surprises? Well, you test it, of course. You could test everything manually by launching the app, using it, and trying your best to make the app blow up with errors. Or you can write a bunch of automated tests, which is arguably a more time-efficient and thorough way to test your apps. Let’s take a look at unit, widget, and integration tests in the video tutorial above.")
                        .length(4 * 60 * 60)
                        .category(categories.get(0))
                        .price(4.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Flutter Bloc Library Tutorial - Reactive State Management")
                        .background("https://i0.wp.com/resocoder.com/wp-content/uploads/2019/10/thumbnail-5.png?fit=300%2C169&")
                        .description("State management is needed by every app. No matter the size of your project, you need to store and do something with all the data present in your app. If you're building something small, you might be able to pull it off with StatefulWidgets. As the difficulty of the project starts to grow, you have to start looking for more maintainable solutions...The flutter_bloc package is a reactive and predictable way to manage your app's state. This package takes everything that's awesome about the BLoC (business logic component) pattern and puts it into a simple-to-use library with amazing tooling. After many months of development, the Bloc package has arrived at its first stable version - 1.0.0.")
                        .length(10 * 60 * 60)
                        .category(categories.get(0))
                        .price(15.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());


                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("100 Days of Code: The Complete Python Pro Bootcamp for 2022")
                        .background("https://img-b.udemycdn.com/course/480x270/2776760_f176_10.jpg")
                        .description("Master Python by building 100 projects in 100 days. Learn data science, automation, build websites, games and apps!")
                        .length(60 * 60 * 60)
                        .category(categories.get(1))
                        .price(72.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Learn Python Programming Masterclass")
                        .background("https://img-b.udemycdn.com/course/240x135/629302_8a2d_2.jpg")
                        .description("This course is aimed at complete beginners who have never programmed before, as well as existing programmers who want to increase their career options by learning Python. The fact is, Python is one of the most popular programming languages in the world – Huge companies like Google use it in mission critical applications like Google Search. And Python is the number one language choice for machine learning, data science and artificial intelligence. To get those high paying jobs you need an expert knowledge of Python, and that’s what you will get from this course.")
                        .length(70 * 60 * 60)
                        .category(categories.get(1))
                        .price(99.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("100 Days of Code: The Complete Python Pro Bootcamp for 2022")
                        .background("https://img-b.udemycdn.com/course/480x270/2776760_f176_10.jpg")
                        .description("Master Python by building 100 projects in 100 days. Learn data science, automation, build websites, games and apps!")
                        .length(14 * 60 * 60)
                        .category(categories.get(1))
                        .price(72.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Introduction to Token Engineering for Web3 Platforms")
                        .background("https://img-b.udemycdn.com/course/240x135/4698152_7a45_3.jpg")
                        .description("Tokens are often touted as investment opportunities but the primary role of tokens on web3 platforms is to capture the value generated by the platforms and distribute value and control among the network participants to incentivize them so that they behave in a way that contributes to the smooth functioning and growth of the ecosystem itself.")
                        .length(1 * 60 * 60)
                        .category(categories.get(2))
                        .price(10.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Ethereum Blockchain Developer Bootcamp With Solidity (2022)")
                        .background("https://img-b.udemycdn.com/course/240x135/1172526_e914_8.jpg")
                        .description("Welcome to the Ethereum Blockchain Developer Bootcamp With Solidity. The only course you'll need to become an Ethereum blockchain developer. With over 1,900 5 stars reviews, this course is one of the highest-rated Ethereum blockchain development courses online.")
                        .length(12 * 60 * 60)
                        .category(categories.get(2))
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
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
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("iOS & Swift - The Complete iOS App Development Bootcamp")
                        .background("https://img-b.udemycdn.com/course/240x135/1778502_f4b9_12.jpg")
                        .description("Welcome to the Complete iOS App Development Bootcamp. With over 39,000 5 star ratings and a 4.8 average my iOS course is the HIGHEST RATED iOS Course in the history of Udemy! At 55+ hours, this iOS 13 course is the most comprehensive iOS development course online! This Swift 5.1 course is based on our in-person app development bootcamp in London, where we've perfected the curriculum over 4 years of in-person teaching. Our complete app development bootcamp teaches you how to code using Swift 5.1 and build beautiful iOS 13 apps for iPhone and iPad. Even if you have ZERO programming experience. I'll take you step-by-step through engaging and fun video tutorials and teach you everything you need to know to succeed as an iOS app developer. The course includes 55+ hours of HD video tutorials and builds your programming knowledge while making real world apps. e.g. Pokemon Go, Whatsapp, QuizUp and Yahoo Weather.")
                        .length(55 * 60 * 60)
                        .category(categories.get(3))
                        .price(55.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("iOS 11 & Swift 4 - The Complete iOS App Development Bootcamp")
                        .background("https://img-b.udemycdn.com/course/240x135/1289478_5831_10.jpg")
                        .description("Welcome to the Complete iOS App Development Bootcamp. With over 39,000 5 star ratings and a 4.8 average my iOS course is the HIGHEST RATED iOS Course in the history of Udemy! At 55+ hours, this iOS 13 course is the most comprehensive iOS development course online! This Swift 5.1 course is based on our in-person app development bootcamp in London, where we've perfected the curriculum over 4 years of in-person teaching. Our complete app development bootcamp teaches you how to code using Swift 5.1 and build beautiful iOS 13 apps for iPhone and iPad. Even if you have ZERO programming experience. I'll take you step-by-step through engaging and fun video tutorials and teach you everything you need to know to succeed as an iOS app developer. The course includes 55+ hours of HD video tutorials and builds your programming knowledge while making real world apps. e.g. Pokemon Go, Whatsapp, QuizUp and Yahoo Weather.")
                        .length(55 * 60 * 60)
                        .category(categories.get(3))
                        .price(55.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("iOS & Swift - The Complete iOS App Development Bootcamp")
                        .background("https://img-b.udemycdn.com/course/240x135/1778502_f4b9_12.jpg")
                        .description("Welcome to the Complete iOS App Development Bootcamp. With over 17,000 5 star ratings, this is the HIGHEST RATED iOS Course of all time! This Swift 4 course is based on our in-person app development bootcamp in London. We've perfected the curriculum over 3 years of in-person teaching. Our complete app development bootcamp teaches you how to code using Swift 4 and build beautiful iOS 11 apps for iPhone and iPad. Even if you have ZERO programming experience.")
                        .length(49 * 60 * 60)
                        .category(categories.get(3))
                        .price(84.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("SwiftUI Masterclass 2022 - iOS 15 App Development & Swift 5")
                        .background("https://img-b.udemycdn.com/course/240x135/1693472_e1d5_13.jpg")
                        .description("Visually learn SwiftUI 3 and build top-notch iOS 15, iPadOS mobile apps, Apple Watch apps, and even macOS desktop applications. This complete iOS application development course is designed to teach you how to become an advanced multiplatform app developer using Apple's native user interface framework: SwiftUI. This class takes learning programming concepts through a project-based approach. By taking this class, you will improve your app design and development skills while creating many hands-on applications.")
                        .length(30 * 60 * 60)
                        .category(categories.get(3))
                        .price(19.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Python and Django Full Stack Web Developer Bootcamp")
                        .background("https://img-b.udemycdn.com/course/240x135/1778502_f4b9_12.jpg")
                        .description("Welcome to the Python and Django Full Stack Web Developer Bootcamp! In this course we cover everything you need to know to build a website using Python, Django, and many more web technologies! Whether you want to change career paths, expand your current skill set, start your own entrepreneurial business, become a consultant, or just want to learn, this is the course for you!")
                        .length(25 * 60 * 60)
                        .category(categories.get(4))
                        .price(20.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Django 4 and Python Full-Stack Developer Masterclass")
                        .background("https://img-b.udemycdn.com/course/240x135/822444_a6db.jpg")
                        .description("Welcome to the best online course for learning how to create websites using Python and Django! In this course we take you from zero to hero in web development with Django! Whether you want to change career paths, expand your current skill set, start your own entrepreneurial business, become a consultant, or just want to learn, this is the course for you!")
                        .length(19 * 60 * 60)
                        .category(categories.get(4))
                        .price(20.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Python Django - The Practical Guide")
                        .background("https://img-b.udemycdn.com/course/240x135/4015616_32a9_2.jpg")
                        .description("And to make that easier, you would typically use a framework like Django - simply because that allows you to focus on your core business logic and you don't need to re-invent the wheel and implement all the nitty-gritty technical details from scratch. And this course is about Django - the most popular Python web development framework out there!")
                        .length(23 * 60 * 60)
                        .category(categories.get(4))
                        .price(20.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("The FastAPI SuperGuide: Create 3 Real-World FastAPI Apps")
                        .background("https://img-b.udemycdn.com/course/240x135/4015616_32a9_2.jpg")
                        .description("Learn FastAPI, Python, REST APIs, Bootstrap, SQLite, Jinja, and web security; all while creating 3 full-stack web apps!")
                        .length(27 * 60 * 60)
                        .category(categories.get(5))
                        .price(20.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("The Complete FastAPI Course With OAuth & JWT Authentication")
                        .background("https://img-b.udemycdn.com/course/240x135/4265666_cd12_10.jpg")
                        .description("FastAPI is one of the most modern, fast and efficient framework for building APIs. If you want to learn how to built high performance APIs then FastAPI should be your go to framework. In this course we will learn FastAPI right from scratch and by then end of the course you will be able to build a complete API which supports authentication, JWT tokens, relational models and protected API routes.")
                        .length(15 * 60 * 60)
                        .category(categories.get(5))
                        .price(30.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("FastAPI - The Complete Course 2022 (Beginner + Advanced)")
                        .background("https://img-b.udemycdn.com/course/240x135/4355412_2065_5.jpg")
                        .description("FastAPI and Python are two of the hottest technologies in the market for building high performing APIs. By the end of this course, you will have built production ready RESTful APIs, a production ready Full Stack application, setup production ready databases, and deployed your FastAPI application so the world can use YOUR app.")
                        .length(34 * 60 * 60)
                        .category(categories.get(5))
                        .price(62.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("The Data Science Course 2022: Complete Data Science Bootcamp")
                        .background("https://img-c.udemycdn.com/course/240x135/2769460_e60c.jpg")
                        .description("Complete Data Science Training: Mathematics, Statistics, Python, Advanced Statistics in Python, Machine & Deep Learning")
                        .length(9 * 60 * 60)
                        .category(categories.get(6))
                        .price(43.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Complete Machine Learning & Data Science Bootcamp 2022")
                        .background("https://funix.edu.vn/wp-content/uploads/2021/08/machine-learning.jpg")
                        .description("Learn Data Science, Data Analysis, Machine Learning (Artificial Intelligence) and Python with Tensorflow, Pandas & more!")
                        .length(16 * 60 * 60)
                        .category(categories.get(6))
                        .price(80.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());

                add(Course.builder()
                        .id(UUID.randomUUID())
                        .title("Bayesian Machine Learning in Python: A/B Testing")
                        .background("https://goldinlocks.github.io/Introduction-to-A-B-testing-in-python/abpro.png")
                        .description("This course is all about A/B testing. A/B testing is used everywhere. Marketing, retail, newsfeeds, online advertising, and more. A/B testing is all about comparing things. If you’re a data scientist, and you want to tell the rest of the company, “logo A is better than logo B”, well you can’t just say that without proving it using numbers and statistics. Traditional A/B testing has been around for a long time, and it’s full of approximations and confusing definitions. In this course, while we will do traditional A/B testing in order to appreciate its complexity, what we will eventually get to is the Bayesian machine learning way of doing things.")
                        .length(10 * 60 * 60)
                        .category(categories.get(6))
                        .price(7.99)
                        .teacher(teachers.get(new Random().nextInt(0, teachers.size())))
                        .build());
            }};

            courses.forEach(courseService::createCourse);

            var sections = initSections(courses);
            initLessons(courses, sections);

            studentJoinCourse(courses);
            initRatings(courses);

        };
    }

    private void studentJoinCourse(List<Course> courses) {
        courses.forEach(course -> {
            if ((new Random().nextInt(0, 3)) % 3 == 0) {
                studentCourseService.studentJoinCourse("hoangthinh@gmail.com", course.getId());
            }
            studentCourseService.studentJoinCourse("student@gmail.com", course.getId());
        });
    }

    private void initRatings(List<Course> courses) {

        var contents = new ArrayList<String>() {{
            add("Amazing stuff, as usual. You're literally the best Flutter Tutor out there. Keep up the good work!");
            add("I applied for a development job and went through 3 interviews. After the 3rd interview, I was given a take-home assignment that requires us to know clean architecture. I know nothing about clean architecture but have experience developing apps. The company gave me some slides to learn clean architecture but I don't think they explain it that well. I am reading your written tutorials and then watching the videos and taking notes. You are saving me! Thank you!");
            add("What an amazing series. I'm so thankful for all of the efforts you put into this series. Keep it up");
            add("This is an amazing course you've put together, really appreciate all the hard work you put into it. In the future would love to hear more about how to handle more complex cases with full CRUD operations, as well as multiple views/blocs/repositories which are interdependent. Looking forward to the next class!");
            add("I love this course.");
            add("Sometimes it is so hard to catchup.");
            add("I do not understand anything");
            add("Perfect explanations, perfect speed.  Fantastic job.  Best Flutter Tutorial I've seen on YouTube! Hats Off to you Sir!");
            add("Thanks for this great video! Coding is easy, but designing a good architecture is quite a challenge.");
            add("I think we could name repositories in domain layer: abstract_repo and the one in the data layer to be: repo_implements. It could be easy to understand the differences as such. My beginner opinion.");
            add("Interested for the next part to see how the design holds up in a larger more complex app. I’ve seen really similar designs in a web service, but I’m worried that doing things like showing data from a cached source before responding with the updated data from an api will be difficult because the repo is separated from the bloc and the domain has to pass the update through.");
            add("A more complex app would have many features that potentially share the same entities and data.  Should those directory structures be up a level, outside the feature tree?");
            add("Nice one. A lot of tutorials are focused on explaing one thing, a couple of them are showing archtectirure patterns, almost none of them are explaing testing. You are planning to create it all together and whats more with TDD. Awsome job and desire to share knowledge. Thanks for that and I hope, We’ll see a lot of videos from you");
            add("OH MY GOD! You make me to understand a lot design, framework , code I have coding 2 year . You're video open my mind forever. Thanks, sir.");
            add("VERY good explanation. Thanks a lot for sharing this with us!");
            add("Hey Reso ! Amazing content I think you should make a video explaining the sequence of videos a person should watch  that you have uploaded  to be a good flutter developer");
            add("Are those features meant to be independent of each other? And if yes, there would be code duplication. What is the best approach in your opinion?");
            add("After watching ton of videos...I finally found yours who took time to go into the depth of things. Thank you");
            add("Nice one. A lot of tutorials are focused on explaing one thing, a couple of them are showing archtectirure patterns, almost none of them are explaing testing. You are planning to create it all together and whats more with TDD. Awsome job and desire to share knowledge. Thanks for that and I hope, We’ll see a lot of videos from you");
            add("Great. There's an \"flutter modular\" that is very similar, if not the same approach. The difference is that they call it modules and not features. Also, has it has a package the are some routing and DI included along side with the pattern. I will watch the playlist, this kind of stuff is really good when the app start to grow. Thanks");
            add("I'm in the middle of my first flutter project, And I wish I had found you earlier. I'm a fan of Uncle Bob and clean architecture.");
            add("Best video I've ever seen about flutter architecture! Thank you very much for the content!");
            add("This is what I need. Love it, big thanks, excellent work dude!");
            add("What an amazing series. I would to know what's the theme are you using in vsCode ?");
            add("Would a mapper substitute the inheritance of models from the entities? (I believe it creates betters separation of concerns)");
            add("Hi. I have question that, we shoud create new feature for each screen or not? because we have pages, widgets for each feature. How do we divide project to features easily?");
        }};

        for (int i = 0; i < courses.size() - 1; i++) {
            for (int j = 0; j < new Random().nextInt(1, contents.size()); j++) {
                ratingService.createRating(RatingRequest.builder()
                        .courseId(courses.get(i).getId())
                        .value(new Random().nextInt(1, 6))
                        .studentId(userService.getUserDetail("student@gmail.com").getId())
                        .content(contents.get(new Random().nextInt(0, contents.size())))
                        .build());
            }
        }
    }


    List<Section> initSections(List<Course> courses) {
        var sections = new ArrayList<Section>();

        courses.forEach(course -> {
            sections.add(sectionService.createSection(SectionRequest.builder()
                    .courseId(course.getId())
                    .title("Introduction")
                    .build()));
        });

        return sections;
    }

    void initLessons(List<Course> courses, List<Section> sections) {

        var videoURLs = new LinkedHashMap<String, String>() {{
            put("Flutter TDD Clean Architecture Course [1] – Explanation & Project Structure", "https://drive.google.com/file/d/1_Cv2uQpic7StIaKlHGirhjGegJl6s1gy/view?usp=sharing");
            put("Flutter TDD Clean Architecture Course [2] – Entities & Use Cases", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B2%5D%20%E2%80%93%20Entities%20%26%20Use%20Cases.mp4");
            put("Flutter TDD Clean Architecture Course [3] – Domain Layer Refactoring", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B3%5D%20%E2%80%93%20Domain%20Layer%20Refactoring.mp4");
            put("Flutter TDD Clean Architecture Course [4] – Data Layer Overview & Models", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B3%5D%20%E2%80%93%20Domain%20Layer%20Refactoring.mp4");
            put("Flutter TDD Clean Architecture Course [5] – Contracts of Data Sources", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B5%5D%20%E2%80%93%20Contracts%20of%20Data%20Sources.mp4");
            put("Flutter TDD Clean Architecture Course [6] – Repository Implementation", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B6%5D%20%E2%80%93%20Repository%20Implementation.mp4");
            put("Flutter TDD Clean Architecture Course [7] – Network Info", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B7%5D%20%E2%80%93%20Network%20Info.mp4");
            put("Flutter TDD Clean Architecture Course [8] – Local Data Source", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B8%5D%20%E2%80%93%20Local%20Data%20Source.mp4");
            put("Flutter TDD Clean Architecture Course [9] – Remote Data Source", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B9%5D%20%E2%80%93%20Remote%20Data%20Source.mp4");
            put("Flutter TDD Clean Architecture Course [10] – Bloc Scaffolding & Input Conversion", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B10%5D%20%E2%80%93%20Bloc%20Scaffolding%20%26%20Input%20Conversion.mp4");
            put("Flutter TDD Clean Architecture Course [11] – Bloc Implementation 1/2", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B11%5D%20%E2%80%93%20Bloc%20Implementation%201_2.mp4");
            put("Flutter TDD Clean Architecture Course [12] – Bloc Implementation 2/2", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B12%5D%20%E2%80%93%20Bloc%20Implementation%202_2.mp4");
            put("Flutter TDD Clean Architecture Course [13] – Dependency Injection", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B13%5D%20%E2%80%93%20Dependency%20Injection.mp4");
            put("Flutter TDD Clean Architecture Course [14] – User Interface", "https://storage.googleapis.com/mi-learning.appspot.com/Flutter%20TDD%20Clean%20Architecture%20Course%20%5B14%5D%20%E2%80%93%20User%20Interface.mp4");
        }};


        sections.forEach(section -> {
                    for (int i = 0; i < videoURLs.size(); i++) {
                        lessonService.createLesson(
                                LessonRequest
                                        .builder()
                                        .isVideo(true)
                                        .lessonOrder(i)
                                        .title(videoURLs.entrySet().stream().toList().get(i).getKey())
                                        .videoUrl(videoURLs.entrySet().stream().toList().get(i).getValue())
                                        .length(new Random().nextInt(0, 60 * 60 + 1))
                                        .sectionId(section.getId())
                                        .build());
                    }
                }

        );
    }

    @Bean
    @Order(7)
    CommandLineRunner initSchedules() {
        return args -> {

            // TODAY
            scheduleService.createSchedule(
                    ScheduleRequest
                            .builder()
                            .email("student@gmail.com")
                            .color(ScheduleColor.BROWN.name())
                            .dueDate(LocalDateTime.now())
                            .location("Microsoft Teams - msjkl")
                            .title("This is a title")
                            .status(ScheduleStatus.COMPLETED.name())
                            .note("This is a noteRemember to bring your own calculator. We don 't have any responsibility to resolve your problem during the test.")
                            .build());

            scheduleService.createSchedule(
                    ScheduleRequest
                            .builder()
                            .email("student@gmail.com")
                            .color(ScheduleColor.GREEN.name())
                            .dueDate(LocalDateTime.now())
                            .location("MS Teams")
                            .title("This is a title")
                            .status(ScheduleStatus.PENDING.name())
                            .note("This is a note")
                            .build());

            scheduleService.createSchedule(
                    ScheduleRequest
                            .builder()
                            .email("student@gmail.com")
                            .color(ScheduleColor.BLUE.name())
                            .dueDate(LocalDateTime.now())
                            .location("MS Teams")
                            .title("This is a title")
                            .status(ScheduleStatus.COMPLETED.name())
                            .note("This is a note")
                            .build());

            // Previous
            scheduleService.createSchedule(
                    ScheduleRequest
                            .builder()
                            .email("student@gmail.com")
                            .color(ScheduleColor.CYAN.name())
                            .dueDate(LocalDateTime.of(2022, 4, 25, 4, 3))
                            .location("MS Teams")
                            .title("This is a title")
                            .status(ScheduleStatus.OVERDUE.name())
                            .note("This is a note")
                            .build());

            scheduleService.createSchedule(
                    ScheduleRequest
                            .builder()
                            .email("student@gmail.com")
                            .color(ScheduleColor.BLUE.name())
                            .dueDate(LocalDateTime.of(2022, 5, 15, 4, 3))
                            .location("MS Teams")
                            .title("This is a title")
                            .status(ScheduleStatus.OVERDUE.name())
                            .note("This is a note")
                            .build());

            scheduleService.createSchedule(
                    ScheduleRequest
                            .builder()
                            .email("student@gmail.com")
                            .color(ScheduleColor.GREEN.name())
                            .dueDate(LocalDateTime.of(2022, 5, 18, 4, 3))
                            .location("MS Teams")
                            .title("This is a title")
                            .status(ScheduleStatus.COMPLETED.name())
                            .note("This is a note")
                            .build());

            // Future
            scheduleService.createSchedule(
                    ScheduleRequest
                            .builder()
                            .email("student@gmail.com")
                            .color(ScheduleColor.RED.name())
                            .dueDate(LocalDateTime.of(LocalDate.of(2022, 6, 1), LocalTime.MIDNIGHT))
                            .location("MS Teams")
                            .title("This is a title")
                            .status(ScheduleStatus.PENDING.name())
                            .note("This is a note")
                            .build());

            scheduleService.createSchedule(
                    ScheduleRequest
                            .builder()
                            .email("student@gmail.com")
                            .color(ScheduleColor.RED.name())
                            .dueDate(LocalDateTime.of(LocalDate.of(2022, 6, 1), LocalTime.MIDNIGHT))
                            .location("MS Teams")
                            .title("This is a title")
                            .status(ScheduleStatus.PENDING.name())
                            .note("This is a note")
                            .build());

        };
    }
}
