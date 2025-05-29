# Marvel Heroes App

Приложение для просмотра информации о героях Marvel с современным интерфейсом и поддержкой двух языков (RU/EN).

<p align="center">
  <img src="screenshots/list_light.png" width="24%">
  <img src="screenshots/detail_light.png" width="24%"> 
  <img src="screenshots/list_dark.png" width="24%">
  <img src="screenshots/detail_dark.png" width="24%">
</p>

##  Особенности
- Полноценная MVI-архитектура
- Поддержка RTL (Right-to-Left) интерфейса
- Реализация edge-to-edge дизайна
- Локализация (русский/английский)
- Оффлайн-режим с кэшированием
- Темная/светлая тема
- Плавные анимации переходов

##  Технологии
- **Jetpack Compose** - современный UI-фреймворк
- **Dagger Hilt** - dependency injection
- **Room** - локальная база данных
- **Retrofit + OkHttp** - работа с API
- **Moshi** - JSON парсинг
- **Marvel API** - источник данных
- **Coil** - загрузка изображений
- **Coroutines + Flow** - асинхронность
- **Navigation Compose** - навигация
- ## 📋 Требования
- Android 8.0+ (API 24)
- Kotlin 1.9.0+
- Android Studio Giraffe+

## ⚙️ Установка
1. Получите API ключи на [developer.marvel.com](https://developer.marvel.com/)
2. Добавьте в `local.properties` :
```properties
MARVEL_PUBLIC_KEY=your_public_key
MARVEL_PRIVATE_KEY=your_private_key
3.  Соберите проект через Android Studio
    

## 📸 Скриншоты

Главный экран

Детали героя

Поиск

Настройки

<img src="screenshots/list.png" width="200">

<img src="screenshots/detail.png" width="200">

<img src="screenshots/search.png" width="200">

<img src="screenshots/settings.png" width="200">

## 📄 Лицензия

Copy

Download

MIT License
Copyright (c) 2023 [Ваше имя]
...

## 📬 Контакты

Ваше имя -  [@your_twitter](https://twitter.com/your_twitter)  -  [your.email@example.com](https://mailto:your.email@example.com/)

Project Link:  [https://github.com/yourname/marvel-heroes](https://github.com/yourname/marvel-heroes)

Copy

Download

### Рекомендации по использованию:
1. Замените `[Ваше имя]` на свои данные
2. Добавьте реальные скриншоты в папку `screenshots/`
3. Обновите ссылки на социальные сети и проект
4. Для лучшего отображения:
   - Используйте изображения в формате WebP (меньший размер)
   - Оптимальный размер скриншотов: 1080x1920 px
   - Для темной/светлой темы можно сделать отдельные скриншоты

Файл готов к размещению в корне вашего GitHub-репозитория!