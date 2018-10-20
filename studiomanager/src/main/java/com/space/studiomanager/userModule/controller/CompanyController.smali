.class public Lcom/space/studiomanager/userModule/controller/CompanyController;
.super Ljava/lang/Object;
.source "CompanyController.java"


# annotations
.annotation runtime Lorg/springframework/web/bind/annotation/RequestMapping;
    value = {
        "/api/company"
    }
.end annotation

.annotation runtime Lorg/springframework/web/bind/annotation/RestController;
.end annotation


# instance fields
.field companyService:Lcom/space/studiomanager/userModule/service/CompanyService;
    .annotation runtime Ljavax/annotation/Resource;
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 17
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public companyLogin(Ljava/util/Map;)Ljava/util/Map;
    .registers 14
    .param p1, "map"    # Ljava/util/Map;
        .annotation runtime Lorg/springframework/web/bind/annotation/RequestBody;
        .end annotation
    .end param
    .annotation runtime Lorg/springframework/web/bind/annotation/RequestMapping;
        value = {
            "/login"
        }
    .end annotation

    .prologue
    .line 24
    new-instance v6, Ljava/util/HashMap;

    invoke-direct {v6}, Ljava/util/HashMap;-><init>()V

    .line 26
    .local v6, "result":Ljava/util/Map;
    const-string v9, "username"

    invoke-interface {p1, v9}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/String;

    .line 27
    .local v8, "username":Ljava/lang/String;
    const-string v9, "password"

    invoke-interface {p1, v9}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    .line 29
    .local v4, "password":Ljava/lang/String;
    sget-object v9, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v11, " "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v9, v10}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 30
    iget-object v9, p0, Lcom/space/studiomanager/userModule/controller/CompanyController;->companyService:Lcom/space/studiomanager/userModule/service/CompanyService;

    invoke-interface {v9, v8, v4}, Lcom/space/studiomanager/userModule/service/CompanyService;->login(Ljava/lang/String;Ljava/lang/String;)Lcom/space/studiomanager/entity/Company;

    move-result-object v2

    .line 32
    .local v2, "login":Lcom/space/studiomanager/entity/Company;
    sget-object v9, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {v9, v2}, Ljava/io/PrintStream;->println(Ljava/lang/Object;)V

    .line 39
    if-eqz v2, :cond_86

    .line 40
    const-string v9, "status"

    const/16 v10, 0xc8

    invoke-static {v10}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v10

    invoke-interface {v6, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 41
    const-string v9, "statusText"

    const-string v10, "ok"

    invoke-interface {v6, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 42
    const-string v9, "currentAuthority"

    const-string v10, "user"

    invoke-interface {v6, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 43
    new-instance v5, Ljava/util/HashMap;

    invoke-direct {v5}, Ljava/util/HashMap;-><init>()V

    .line 44
    .local v5, "profile":Ljava/util/Map;
    invoke-virtual {v2}, Lcom/space/studiomanager/entity/Company;->getAccount()Ljava/lang/String;

    move-result-object v3

    .line 45
    .local v3, "name":Ljava/lang/String;
    const-string v1, "\u6296\u97f3"

    .line 46
    .local v1, "department":Ljava/lang/String;
    const-string v0, "https://img.alicdn.com/tfs/TB1L6tBXQyWBuNjy0FpXXassXXa-80-80.png"

    .line 47
    .local v0, "avatar":Ljava/lang/String;
    invoke-virtual {v2}, Lcom/space/studiomanager/entity/Company;->getId()I

    move-result v7

    .line 48
    .local v7, "userid":I
    const-string v9, "name"

    invoke-interface {v5, v9, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 49
    const-string v9, "department"

    invoke-interface {v5, v9, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 50
    const-string v9, "avatar"

    invoke-interface {v5, v9, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 51
    const-string v9, "userid"

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v10

    invoke-interface {v5, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 52
    const-string v9, "profile"

    invoke-interface {v6, v9, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 59
    .end local v0    # "avatar":Ljava/lang/String;
    .end local v1    # "department":Ljava/lang/String;
    .end local v3    # "name":Ljava/lang/String;
    .end local v5    # "profile":Ljava/util/Map;
    .end local v7    # "userid":I
    :goto_85
    return-object v6

    .line 54
    :cond_86
    const-string v9, "status"

    const/16 v10, 0x191

    invoke-static {v10}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v10

    invoke-interface {v6, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 55
    const-string v9, "statusText"

    const-string v10, "unauthorized"

    invoke-interface {v6, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 56
    const-string v9, "currentAuthority"

    const-string v10, "guest"

    invoke-interface {v6, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_85
.end method
