# EventFlow Platform

Modern bir etkinlik yönetimi ve bilet satış platformu.

## 🏗️ Mimari

<img width="945" height="507" alt="schema" src="https://github.com/user-attachments/assets/2d0d6627-d337-4c59-aafc-2526e235a89b" />


### Servis Haritası

| Servis | Port | Veritabanı | Açıklama |
|--------|------|------------|----------|
| **API Gateway** | 8080 | - | Tüm isteklerin giriş noktası |
| **Eureka Server** | 8761 | - | Servis keşfi ve kayıt |
| **Config Server** | 8888 | - | Merkezi konfigürasyon |
| **User Service** | 8081 | PostgreSQL | Kullanıcı yönetimi |
| **Auth Service** | 8082 | Redis | JWT token yönetimi |
| **Event Service** | 8083 | PostgreSQL | Etkinlik yönetimi |
| **Ticket Service** | 8084 | PostgreSQL | Bilet satış ve yönetimi |

## 🛠️ Teknolojiler

- **Java 21** - Programlama dili
- **Spring Boot 3.5** - Framework
- **Spring Cloud** - Mikroservis altyapısı
  - Netflix Eureka (Service Discovery)
  - Spring Cloud Config (Merkezi konfigürasyon)
  - Spring Cloud Gateway (API Gateway)
  - OpenFeign (Servisler arası iletişim)
- **PostgreSQL** - Veritabanı
- **Redis** - Token cache
- **RabbitMQ** - Mesaj kuyruğu
- **MyBatis** - ORM
- **Docker** - Konteynerizasyon

## 📦 Servisler

| Servis | Port | Açıklama |
|--------|------|----------|
| Discovery Service | 8761 | Eureka Server - Servis keşfi |
| Config Service | 8888 | Merkezi konfigürasyon yönetimi |
| Gateway Service | 8080 | API Gateway - Yönlendirme ve güvenlik |
| Auth Service | 8082 | Kimlik doğrulama ve JWT token yönetimi |
| User Service | 8081 | Kullanıcı yönetimi |
| Event Service | 8083 | Etkinlik yönetimi |
| Ticket Service | 8084 | Bilet satış ve yönetimi |

## 🚀 Kurulum

### Gereksinimler
- Java 21+
- Docker & Docker Compose
- Gradle

### Çalıştırma

1. **Projeyi klonlayın:**
```bash
git clone https://github.com/your-username/eventflow-platform.git
cd eventflow-platform
```

2. **Projeyi derleyin:**
```bash
./gradlew build
```

3. **Docker ile çalıştırın:**
```bash
docker-compose up -d
```

4. **Servislerin ayağa kalkmasını bekleyin ve test edin:**
```bash
# Eureka Dashboard
http://localhost:8761

# API Gateway
http://localhost:8080
```

## 📚 API Endpoints

### Auth Service
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| POST | `/api/auth/register` | Kullanıcı kaydı |
| POST | `/api/auth/login` | Giriş yapma |
| POST | `/api/auth/validate` | Token doğrulama |

### User Service
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/users/{id}` | Kullanıcı bilgisi |
| GET | `/api/users/me` | Mevcut kullanıcı |
| PUT | `/api/users/{id}` | Kullanıcı güncelleme |

### Event Service
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/events` | Tüm etkinlikler |
| GET | `/api/events/{id}` | Etkinlik detayı |
| POST | `/api/events` | Etkinlik oluşturma |
| PUT | `/api/events/{id}` | Etkinlik güncelleme |
| DELETE | `/api/events/{id}` | Etkinlik silme |
| POST | `/api/events/{id}/cancel` | Etkinlik iptali |

### Ticket Service
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| POST | `/api/tickets/buy` | Bilet satın alma |
| GET | `/api/tickets/my` | Kullanıcının biletleri |
| GET | `/api/tickets/{id}` | Bilet detayı |
| POST | `/api/tickets/{id}/cancel` | Bilet iptali |
| GET | `/api/tickets/event/{eventId}` | Etkinliğin biletleri |

## 🔐 Güvenlik

- JWT tabanlı kimlik doğrulama
- Gateway seviyesinde token validasyonu
- Rol tabanlı yetkilendirme (User, Admin, Event Owner, Moderator)
- Redis ile token cache

## 📨 Mesajlaşma

Event Service ve Ticket Service arasında RabbitMQ ile asenkron iletişim:

- Bilet satıldığında → `sold_tickets` sayısı artırılır
- Bilet iptal edildiğinde → `sold_tickets` sayısı azaltılır

## 🧪 Test

```bash
# Tüm testleri çalıştır
./gradlew test

# Belirli bir servisin testlerini çalıştır
./gradlew :event-service:test
```

## 📝 Lisans

MIT License

## 👤 Geliştirici

Yiğit Üçün

