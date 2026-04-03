# Godot GameAnalytics 7.0.1 SDK
[![GameAnalytics](https://img.shields.io/badge/GameAnalytics_7.0.1-black?style=for-the-badge&logoSize=auto)](https://gameanalytics.com/)
[![Godot](https://img.shields.io/badge/Godot%20Engine-3.6.2-blue?style=for-the-badge&logo=godotengine&logoSize=auto)](https://godotengine.org/)
[![Godot](https://img.shields.io/badge/Godot%20Engine-4.6.1-blue?style=for-the-badge&logo=godotengine&logoSize=auto)](https://godotengine.org/)
[![GitHub License](https://img.shields.io/github/license/damnedpie/godot-gameanalytics?style=for-the-badge)](https://github.com/damnedpie/godot-gameanalytics/blob/main/LICENSE)
[![GitHub Repo stars](https://img.shields.io/github/stars/damnedpie/godot-gameanalytics?style=for-the-badge&logo=github&logoSize=auto&color=%23FFD700)](https://github.com/damnedpie/godot-gameanalytics/stargazers)

GameAnalytics 7.0.1 SDK Android plugin for Godot. Built on Godot 3.6.2 / Godot 4.6.1 dependency.

## Author's note

[Based on the official repository.](https://github.com/GameAnalytics/GA-SDK-GODOT) Main difference between my repo and the official one is that this repo uses up to date dependencies and has some issues fixed, plus a well documented GDScript wrapper for the SDK. Please take into account that GameAnalytics is not just an MMP but basically a framework in terms of how you set your in-game events, so make sure you study the docs thoroughly before using it.

## Setup

### Project integration

Grab the``GodotGameAnalytics`` plugin binary (.aar) and config (.gdap) from `godot3` or `godot4` folder and put both into ``res://android/plugins``. For easy start, you can also use ``godot_game_analytics.gd`` script as an autoload.

Make sure that you provide the AppKey and the SecretKey during initialization.
