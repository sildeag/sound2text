# Sound2Text

[![Build Status](https://github.com/sildeag/sound2text/actions/workflows/ci.yml/badge.svg)](https://github.com/sildeag/sound2text/actions/workflows/ci.yml)
[![Test Coverage](https://img.shields.io/codecov/c/github/sildeag/sound2text?color=green)](https://codecov.io/gh/sildeag/sound2text)
[![Docs](https://img.shields.io/badge/docs-online-blue)](https://sildeag.github.io/sound2text/)
[![License](https://img.shields.io/github/license/sildeag/sound2text)](LICENSE)

## Work in Progress

`sound2text` is an active engineering workbench rather than a finished end-user release. Architecture, module boundaries, and runtime wiring are evolving rapidly, so expect rough edges if you clone and build it today.

## Current Status

`sound2text` is in active development as its architecture is refactored into decoupled, dynamically loaded feature plugins.

- **Architecture & Build System:** Core infrastructure—including Gradle convention plugins, version catalogs, and Koin-based dynamic feature discovery—is in place and compiles cleanly.
- **PDF & Form Workflow (`:feature-pdf` / `PdfWizard`):** Actively being migrated into `sound2text`. Running `./gradlew :appcommon:run` works locally in testing (currently limited to displaying one or more dynamic features), though those changes are pending on GitHub alongside updated `:core` and `:ui-common` modules. To avoid duplicate rework, integration on this repo is paused while `:feature-stt` and `:stt-*` are consolidated in the companion `wac2wav` workspace—both will be ported over together once ready.
- **Speech-to-Text (`:feature-stt` & `:stt-*`):** Offline transcription engines (Vosk and Whisper) are being wired up and stabilized in `wac2wav` prior to migration into this repository.

## What to Expect on a Fresh Clone

If you clone and open this repository in Android Studio or IntelliJ IDEA today:

- **Gradle Sync:** Syncs and resolves dependencies cleanly out of the box.
- **Desktop Execution:** On the current public `main` branch, `:appcommon` does not yet have a complete end-to-end execution path because the updated `:core` and `:ui-common` modules are still awaiting push. Once synced with local changes, `./gradlew :appcommon:run` launches the dynamic feature shell.
- **Android Target:** Requires the remaining `:feature-stt` and `:feature-pdf` migrations to complete before full end-to-end workflows are available on device.

## Exploring Kotlin Multiplatform

If you are exploring the [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) structure or experimenting with the modules, feel free to open an issue or discussion with what you ran into—just keep in mind some pieces are still mid-flight.

Sound2Text is a cross‑platform application and audio‑to‑text pipeline built with Kotlin Multiplatform.  
It captures spoken input — from a microphone or from audio files — and converts it into structured text using pluggable speech‑to‑text engines.  
The project is designed to support real applications such as populating PDF form fields, capturing genealogical memories, recording medical notes, and enabling multilingual voice‑driven data entry.

Sound2Text is a Kotlin Multiplatform Compose application architected from scratch to serve as a working reference for multiplatform development across Android, Desktop (JVM), iOS, JS, and WASM.  
It was not cloned from existing templates — its structure emerged through direct experimentation, plugin alignment, and source‑set validation.  
Later comparisons to JetBrains' BasicView and KotlinProject revealed striking architectural convergence, validating the clarity and resilience of this design.

---

## What Sound2Text Is

Sound2Text provides a clean, practical foundation for speech‑driven workflows.  
It offers:

- live microphone capture
- file‑based audio processing
- a unified configuration system
- modular UI layers
- pluggable STT engines (starting with Vosk)
- CPU‑friendly operation suitable for everyday hardware

The architecture emphasizes clarity, modularity, and real‑world usefulness — not just a framework, but a tool that can support meaningful tasks.

---

## What Sound2Text Is Not

Sound2Text is not a full audio workstation, a GPU‑accelerated ML framework, or a research‑grade DSP toolkit.  
It does not aim to provide advanced audio editing, neural‑network training, or heavy signal‑processing features.

Its focus is practical and specific:  
**turning spoken words into usable text through a clean, extensible pipeline.**

---

## Why Sound2Text Exists

The project began with a simple, real‑world need:  
**to fill PDF forms using voice input in a fast, accurate, and distraction‑free way.**

From there, the concept expanded to support other human‑scale tasks:

- capturing family stories and genealogical memories
- parsing PDF census and other information into GEDCOM
- recording medical notes for patient records
- enabling voice‑driven data entry
- creating a database bridge to other applications
- supporting multilingual workflows (starting with English and Spanish)

Sound2Text aims to make speech input accessible and useful without requiring specialized hardware or heavyweight frameworks.

---

## Architectural Principles

Sound2Text is built around a set of guiding principles that keep the project maintainable and aligned with modern Kotlin development:

- **Shared logic in `core`**  
  Engines, settings, and abstractions live in a common module.

- **Platform‑specific audio capture in UI modules**  
  Java Sound on desktop, AudioRecord on Android, FFmpeg as a universal fallback.

- **Unified configuration (`config.json`)**  
  A single, typed source of truth for audio and STT settings.

- **Pluggable STT engines**  
  Vosk today, with the architecture ready for additional engines and multilingual support.

- **CPU‑friendly by design**  
  No GPU assumptions; optimized for typical desktops and laptops.

- **Composable pipeline**  
  Audio source → preprocessing → STT engine → structured output.

- **Modern Kotlin tooling**  
  Dependency injection patterns, unit testing, Dokka documentation, and JaCoCo coverage support long‑term clarity and reliability.

---

## Features

- Core sound‑to‑text engine
- Desktop and Android storage services
- Multiplatform DI with Koin
- Auto‑generated documentation with Dokka
- Unified configuration system
- Modular UI layers
- Typed Gradle/TOML version catalog
- Compose Multiplatform support across JVM, Android, iOS, JS, and WASM

---

## Under Development

Structure stable; runnable legs pending integration of the new configuration system, Vosk wiring, and desktop audio capture.

Sound2Text was architected from scratch as a working reference for Compose Multiplatform development.  
Its structure emerged through direct experimentation, plugin alignment, and source‑set validation — not from cloning templates.  
Later comparisons to JetBrains’ BasicView and KotlinProject revealed strong architectural convergence, validating the clarity and resilience of this design.

---

## 📘 Purpose

- Provide a stable, working baseline for Compose Multiplatform development
- Serve as a contributor‑friendly reference for onboarding and extension
- Document hard‑won insights into plugin placement, TOML version catalogs, and multiplatform quirks
- Offer a type‑safe, TOML‑driven Gradle setup using Kotlin 2.3.0 and Compose 1.9.3 and 2.2.21
- Enable future integration of sound‑to‑text input, voice‑activated dropdowns, and multilingual support (starting with Spanish)

---

## 🧠 What We Learned the Hard Way

Sound2Text reflects direct experience with multiplatform setup — not just what works, but *why* it works.

- **Plugin Placement Matters:** Plugins must be declared at the module root, not inside `sourceSets`.
- **Source Set Naming:** Use `jvmMain` for desktop targets inside template apps; reserve `desktopMain` for standalone desktop modules.
- **TOML Catalog Scoping:** Dependencies must be scoped inside `sourceSets.dependencies {}` blocks to resolve `libs.` aliases.
- **Resource Integration:** `compose-resources` generates assets under a generated package; imports must match accordingly.
- **Validation Through Convergence:** The architecture aligns with JetBrains' idiomatic multiplatform structure — confirming its durability.

---

## 🛠 Technical Guidance from Google Gemini and Copilot

Sound2Text was developed with architectural and troubleshooting support from Google Gemini, Microsoft Copilot and AS Gemini, whose guidance helped clarify plugin alignments, loosely coupled dynamic feature detection, TOML scoping, and multiplatform quirks.  
Copilot assisted in original development, validating decisions, and Sentinel; AS Gemini in troubleshooting KMP, edge cases, and shaping functionality.

## 📚 Documentation & Wiki

The Sound2Text wiki contains deeper technical notes, architectural diagrams, module explanations, and ongoing development insights.

👉 **Visit the wiki:** https://github.com/sildeag/sound2text/wiki
